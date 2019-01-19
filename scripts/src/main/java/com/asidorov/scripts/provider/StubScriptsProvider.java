package com.asidorov.scripts.provider;

import com.asidorov.scripts.model.VulnerabilityScript;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StubScriptsProvider implements ScriptsProvider{
    @Override
    public List<VulnerabilityScript> getAllScriptsForExecution() {
        return Collections.emptyList();
    }

    @Override
    public List<VulnerabilityScript> findScriptsByIds(Set<Integer> scriptIds) {
        return Collections.emptyList();
    }
}
