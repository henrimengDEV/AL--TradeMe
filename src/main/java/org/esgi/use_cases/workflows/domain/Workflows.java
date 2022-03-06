package org.esgi.use_cases.workflows.domain;

import org.esgi.use_cases.member.domain.model.MemberId;

public interface Workflows {

    WorkflowsId getWorkflowsId();

    String getContent();

    MemberId getMemberId();


}
