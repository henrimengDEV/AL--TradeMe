package org.esgi.use_cases.project.domain;

import org.esgi.kernel.ValueObject;

@ValueObject
public class ProjectAddress {

    private final String localisation;

    private ProjectAddress(String localisation) {
        this.localisation = localisation;
    }

    public static ProjectAddress of(String localisation) {
        return new ProjectAddress(localisation);
    }

    public String getLocalisation() {
        return localisation;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + localisation + '\'' +
                '}';
    }
}
