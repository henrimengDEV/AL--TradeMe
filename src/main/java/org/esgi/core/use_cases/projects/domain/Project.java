package org.esgi.core.use_cases.projects.domain;

import org.esgi.core.use_cases.member.domain.model.MemberId;

import java.util.List;

public interface Project {

    ProjectId getProjectId();

    List<MemberId> getParticipants();


}
