package ma.dnaengineering.backend.service;

import ma.dnaengineering.backend.entity.Employee;
import ma.dnaengineering.backend.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private EmployeeRepository repository;
    @Override
    public boolean hasCSVformat(MultipartFile file) {
        String type = "text/csv";
        if(!type.equals(file.getContentType()))
            return false;
        return true;
    }

    @Override
    public void SaveData(MultipartFile file) {
        try {
            List<Employee> employees = csvToEmployee(file.getInputStream());
            repository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Employee> csvToEmployee(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvparser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<Employee> employees= new ArrayList<>();
            List<CSVRecord> records = csvparser.getRecords();
            for (CSVRecord csvRecord : records) {
                Employee employee = new Employee(
                        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("name"),
                        csvRecord.get("jobTitle"),
                        csvRecord.get("salary"));
                employees.add(employee);
            }
            return employees;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
