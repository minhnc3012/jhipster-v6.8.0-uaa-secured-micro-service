package com.formos.crm;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.formos.crm");

        noClasses()
            .that()
                .resideInAnyPackage("com.formos.crm.service..")
            .or()
                .resideInAnyPackage("com.formos.crm.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.formos.crm.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
