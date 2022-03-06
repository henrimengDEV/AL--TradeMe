package org.esgi.core.use_cases.workflows.domain;

import org.esgi.core.use_cases.member.domain.model.MemberId;

public interface Workflows {

    WorkflowsId getWorkflowsId();

    String getContent();

    MemberId getMemberId();


}
