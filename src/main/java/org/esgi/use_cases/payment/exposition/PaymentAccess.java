package org.esgi.use_cases.payment.exposition;

import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;

public class PaymentAccess {

    public final CommandBus commandBus;
    public final QueryBus   queryBus;

    public PaymentAccess(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
}
