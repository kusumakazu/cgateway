package com.id.kusumakazu;

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
            .importPackages("com.id.kusumakazu");

        noClasses()
            .that()
                .resideInAnyPackage("com.id.kusumakazu.service..")
            .or()
                .resideInAnyPackage("com.id.kusumakazu.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.id.kusumakazu.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
