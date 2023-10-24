package utils;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reports {

    public static void generateReport() {
        // Set the report output directory
        File reportOutputDirectory = new File("target/cucumber-reports");

        // List the JSON report files
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-report.json");

        // Set the report configuration
        Configuration configuration = new Configuration(reportOutputDirectory, "MAFAO - Web");
        configuration.setBuildNumber("1.0");
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Chrome");

        // Create the report builder and generate the report
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}