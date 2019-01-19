package com.asidorov.scripts.provider;

import com.asidorov.scripts.model.VulnerabilityScript;

import java.util.List;
import java.util.Set;

public interface ScriptsProvider {
    List<VulnerabilityScript> getAllScriptsForExecution();

    List<VulnerabilityScript> findScriptsByIds(Set<Integer> scriptIds);

}
