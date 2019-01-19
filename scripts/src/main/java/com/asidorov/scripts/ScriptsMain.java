package com.asidorov.scripts;

import com.asidorov.scripts.model.VulnerabilityScript;
import com.asidorov.scripts.service.ScriptExecutionService;

import java.util.List;

public class ScriptsMain {

    public static void main(String[] args) {
        ScriptExecutionService executionService = new ScriptExecutionService();

        List<VulnerabilityScript> result = executionService.getScriptsForExecution();
    }
}
