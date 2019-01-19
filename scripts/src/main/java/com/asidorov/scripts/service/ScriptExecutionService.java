package com.asidorov.scripts.service;

import com.asidorov.scripts.model.VulnerabilityScript;
import com.asidorov.scripts.provider.ScriptsProvider;
import com.asidorov.scripts.provider.StubScriptsProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScriptExecutionService {

    private final ScriptsProvider provider = new StubScriptsProvider();

    public List<VulnerabilityScript> getScriptsForExecution() {
        List<VulnerabilityScript> scriptsToBeExecuted = provider.getAllScriptsForExecution();

        //accumulation collection which will store all dependencies from highest to deepest
        List<VulnerabilityScript> scriptsInOrderReadyForExecution = new ArrayList<>(scriptsToBeExecuted);

        processDependencies(scriptsToBeExecuted, scriptsInOrderReadyForExecution);

        //reverse accumulated scripts from deepest to highest level
        Collections.reverse(scriptsInOrderReadyForExecution);

        return scriptsInOrderReadyForExecution;
    }

    /**
     * Method is designed to accumulate all nested dependencies recursively
     *
     * @param scriptsToBeExecuted             current scripts with nested dependencies
     * @param scriptsInOrderReadyForExecution accumulator for scripts
     */
    private void processDependencies(List<VulnerabilityScript> scriptsToBeExecuted, List<VulnerabilityScript> scriptsInOrderReadyForExecution) {
        //Set is desired to be used instead of list for several purposes: there is no need in ordering dependencies on one level and for avoiding duplicates
        Set<Integer> uniqueDependenciesIds = scriptsToBeExecuted.stream()
                .flatMap(script -> script.getDependencies().stream())
                .collect(Collectors.toSet());
        if (!uniqueDependenciesIds.isEmpty()) {
            List<VulnerabilityScript> currentDependencies = provider.findScriptsByIds(uniqueDependenciesIds);
            scriptsInOrderReadyForExecution.addAll(currentDependencies);
            processDependencies(currentDependencies, scriptsInOrderReadyForExecution);
        }
    }
}
