package org.esgi.use_cases.projects.domain;

import org.esgi.use_cases.member.domain.model.MemberId;

import java.util.List;

public interface Project {

    ProjectId getProjectId();

    List<MemberId> getParticipants();


}
