package org.esgi.controllers.market.response;

import java.util.List;

public class ProjectResponse {

    public final Integer projectId;

    public final List<Integer> particpants;

    public ProjectResponse(Integer projectId, List<Integer> particpants) {
        this.projectId = projectId;
        this.particpants = particpants;}
}
