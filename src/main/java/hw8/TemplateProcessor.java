package hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateProcessor {
    private String templateFile;
    private String templateContent;
    private CsvParser csv;

    public TemplateProcessor(String templateFile, CsvParser file) throws IOException {
        if (file == null) throw new IllegalArgumentException("csv file cannot be null!");
        BufferedReader reader = new BufferedReader(new FileReader(templateFile));
        StringBuilder content = new StringBuilder();
        String eachLine;
        while ((eachLine = reader.readLine()) != null) {
            content.append(eachLine).append(System.lineSeparator());
        }
        reader.close();
        this.csv = file;
        this.templateFile = templateFile;
        this.templateContent = content.toString();
    }

    public String replaceHolders(HashMap<String, String> customerMap) {
        StringBuilder res = new StringBuilder();
        Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
        Matcher matcher = pattern.matcher(this.templateContent);
        while (matcher.find()) {
            String placeHolder = matcher.group(1);
            String value = customerMap.get(placeHolder);
            if (value != null) {
                matcher.appendReplacement(res, value);
            }
            else throw new IllegalArgumentException("Cannot find the placeHolder value!");
        }
        matcher.appendTail(res);
        return res.toString();
    }

    public void writeFile(String content, String directoryPath, String fileName) throws IOException{
        File file = new File(directoryPath);
        file.mkdirs();
        String fullPath = directoryPath + File.separator + fileName;
        file = new File(fullPath);
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

    public void writeAllFiles(List<HashMap<String, String >> customerMapList, String directoryPath) throws IOException{
        for (HashMap<String, String> customerMap : customerMapList) {
            String content = replaceHolders(customerMap);
            String fileName =  customerMap.get(this.csv.getHeader().get(0)) + "-" + this.templateFile;
            writeFile(content, directoryPath, fileName);
        }
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public CsvParser getCsv() {
        return csv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemplateProcessor that = (TemplateProcessor) o;
        return Objects.equals(templateFile, that.templateFile) && Objects.equals(
            templateContent, that.templateContent) && Objects.equals(csv, that.csv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateFile, templateContent) + csv.hashCode();
    }

    @Override
    public String toString() {
        return "TemplateProcessor{" +
            "templateFile='" + templateFile + '\'' +
            ", templateContent='" + templateContent + '\'' +
            ", csv=" + csv +
            '}';
    }
}
