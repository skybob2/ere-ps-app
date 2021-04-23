package health.ere.ps.validation.fhir.structuredefinition.fhir.kbv.de.v1_0_3;

import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.util.Calendar;

public class KBV_PR_FOR_Patient_StructureDefinition extends StructureDefinition {

    public KBV_PR_FOR_Patient_StructureDefinition() {
        setUrl("https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient");
        setVersion("1.0.3");
        setName("KBV_PR_FOR_Patient");

        Calendar calendar = Calendar.getInstance();

        calendar.set(2021, 2, 23);

        setDate(calendar.getTime());

        setStatus(Enumerations.PublicationStatus.ACTIVE);
        setPublisher("Kassen&#228;rztliche Bundesvereinigung");
        setFhirVersion(Enumerations.FHIRVersion.fromCode("4.0.1"));

        addMapping()
                .setIdentity("rim")
                .setUri("http://hl7.org/v3")
                .setName("RIM Mapping");
        addMapping()
                .setIdentity("cda")
                .setUri("http://hl7.org/v3/cda")
                .setName("CDA (R2)");
        addMapping()
                .setIdentity("w5")
                .setUri("http://hl7.org/fhir/fivews")
                .setName("FiveWs Pattern Mapping");
        addMapping()
                .setIdentity("v2")
                .setUri("http://hl7.org/v2")
                .setName("HL7 v2 Mapping");
        addMapping()
                .setIdentity("loinc")
                .setUri("http://loinc.org")
                .setName("LOINC code for the element");

        setKind(StructureDefinitionKind.RESOURCE);
        setAbstract(false);
        setType("Patient");
        setBaseDefinition("https://fhir.kbv.de/StructureDefinition/KBV_PR_Base_Patient|1.1.3");
        setDerivation(TypeDerivationRule.CONSTRAINT);

        StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent = getSnapshot();

        initElements(snapshotComponent);
    }

    protected void initElements(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        // Patient.id
        initElementPatientId(snapshotComponent);

        // Patient.meta
        initElementPatientMeta(snapshotComponent);

        // Patient.meta.profile
        initElementPatientMetaProfile(snapshotComponent);

        // Patient.identifier
        initElementPatientIdentifier(snapshotComponent);

        // Patient.identifier:versichertenId_GKV
        initElementPatientIdentifierVersichertenId_GKV(snapshotComponent);

        // Patient.identifier:versichertenId_GKV.type
        initElementPatientIdentifierVersichertenId_GKV_Type(snapshotComponent);

        // Patient.identifier:versichertenId_GKV.type.coding
        initElementPatientIdentifierVersichertenId_GKV_Type_Coding(snapshotComponent);

        // Patient.identifier:versichertenId_GKV.type.coding.system
        initElementPatientIdentifierVersichertenId_GKV_Type_Coding_System(snapshotComponent);

        // Patient.identifier:versichertenId_GKV.type.coding.code
        initElementPatientIdentifierVersichertenId_GKV_Type_Coding_Code(snapshotComponent);

        // Patient.identifier:versichertenId_GKV.system
        initElementPatientIdentifierVersichertenId_GKV_System(snapshotComponent);

        // Patient.identifier:versichertenId_GKV.value
        initElementPatientIdentifierVersichertenId_GKV_Value(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv
        initElementPatientIdentifierVersichertennummer_PKV(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.type
        initElementPatientIdentifierVersichertennummer_PKV_Type(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.type.coding
        initElementPatientIdentifierVersichertennummer_PKV_Type_Coding(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.type.coding.system
        initElementPatientIdentifierVersichertennummer_PKV_Type_Coding_System(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.type.coding.code
        initElementPatientIdentifierVersichertennummer_PKV_Type_Coding_Code(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.system
        initElementPatientIdentifierVersichertennummer_PKV_System(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.value
        initElementPatientIdentifierVersichertennummer_PKV_Value(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.assigner
        initElementPatientIdentifierVersichertennummer_PKV_Assigner(snapshotComponent);

        // Patient.identifier:versichertennummer_pkv.assigner.display
        initElementPatientIdentifierVersichertennummer_PKV_Assigner_Display(snapshotComponent);

        // Patient.identifier:versichertennummer_kvk
        initElementPatientIdentifierVersichertennummer_KVK(snapshotComponent);

        // Patient.identifier:versichertennummer_kvk.type
        initElementPatientIdentifierVersichertennummer_KVK_Type(snapshotComponent);

        // Patient.identifier:versichertennummer_kvk.type.coding
        initElementPatientIdentifierVersichertennummer_KVK_Type_Coding(snapshotComponent);

        // Patient.identifier:versichertennummer_kvk.type.coding.system
        initElementPatientIdentifierVersichertennummer_KVK_Type_Coding_System(snapshotComponent);

        // Patient.identifier:versichertennummer_kvk.type.coding.code
        initElementPatientIdentifierVersichertennummer_KVK_Type_Coding_Code(snapshotComponent);

        // Patient.name
        initElementPatientName(snapshotComponent);

        // Patient.name:name
        initElementPatientName_Name(snapshotComponent);

        // Patient.name:name.use
        initElementPatientName_Name_Use(snapshotComponent);

        // Patient.name:name.family
        initElementPatientName_Name_Family(snapshotComponent);

        // Patient.name:name.family.extension:namenszusatz
        initElementPatientName_Name_Family_Ext_Namenszusatz(snapshotComponent);

        // Patient.name:name.family.extension:namenszusatz.value[x]
        initElementPatientName_Name_Family_Ext_Namenszusatz_Value_X(snapshotComponent);

        // Patient.name:name.family.extension:namenszusatz.value[x]:valueString
        initElementPatientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString(snapshotComponent);

        // Patient.name:name.family.extension:nachname
        initElementPatientName_Name_Family_Ext_Nachname(snapshotComponent);

        // Patient.name:name.family.extension:nachname.value[x]
        initElementPatientName_Name_Family_Ext_Nachname_Value_X(snapshotComponent);

        // Patient.name:name.family.extension:nachname.value[x]:valueString
        initElementPatientName_Name_Family_Ext_Nachname_Value_X_ValueString(snapshotComponent);

        // Patient.name:name.family.extension:vorsatzwort
        initElementPatientName_Name_Family_Ext_Vorsatzwort(snapshotComponent);

        // Patient.name:name.family.extension:vorsatzwort.value[x]
        initElementPatientName_Name_Family_Ext_Vorsatzwort_Value_X(snapshotComponent);

        // Patient.name:name.family.extension:vorsatzwort.value[x]:valueString
        initElementPatientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString(snapshotComponent);

        // Patient.name:name.given
        initElementPatientName_Name_Given(snapshotComponent);

        // Patient.name:name.prefix
        initElementPatientName_Name_Prefix(snapshotComponent);

        // Patient.name:name.prefix.extension:prefix-qualifier
        initElementPatientName_Name_Prefix_Ext_Prefix_Qualifier(snapshotComponent);

        // Patient.name:name.prefix.extension:prefix-qualifier.value[x]
        initElementPatientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X(snapshotComponent);

        // Patient.name:name.prefix.extension:prefix-qualifier.value[x]:valueCode
        initElementPatientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode(snapshotComponent);

        // Patient.birthDate
        initElementPatientBirthDate(snapshotComponent);

        // Patient.birthDate.extension:data-absent-reason
        initElementPatientBirthDate_Ext_Data_Absent_Reason(snapshotComponent);

        // Patient.birthDate.extension:data-absent-reason.value[x]
        initElementPatientBirthDate_Ext_Data_Absent_Reason_Value_X(snapshotComponent);

        // Patient.birthDate.extension:data-absent-reason.value[x]:valueCode
        initElementpPatientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode(snapshotComponent);

        // Patient.address
        initElementPatientAddress(snapshotComponent);

        // Patient.address:Strassenanschrift
        initElementPatientAddress_Strassenanschrift(snapshotComponent);

        // Patient.address:Strassenanschrift.type
        initElementPatientAddress_Strassenanschrift_Type(snapshotComponent);

        // Patient.address:Strassenanschrift.line
        initElementPatientAddress_Strassenanschrift_Line(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Strasse
        initElementPatientAddress_Strassenanschrift_Line_Ext_Strasse(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Strasse.value[x]
        initElementPatientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Strasse.value[x]:valueString
        initElementPatientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString(
                snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Hausnummer
        initElementPatientAddress_Strassenanschrift_Line_Ext_Hausnummer(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Hausnummer.value[x]
        initElementPatientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Hausnummer.value[x]:valueString
        initElementPatientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString(
                snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Adresszusatz
        initElementPatientAddress_Strassenanschrift_Line_Ext_Adresszusatz(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Adresszusatz.value[x]
        initElementPatientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X(snapshotComponent);

        // Patient.address:Strassenanschrift.line.extension:Adresszusatz.value[x]:valueString
        initElementPatientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString(
                snapshotComponent);

        // Patient.address:Strassenanschrift.city
        initElementPatientAddress_Strassenanschrift_City(snapshotComponent);

        // Patient.address:Strassenanschrift.postalCode
        initElementPatientAddress_Strassenanschrift_PostalCode(snapshotComponent);

        // Patient.address:Strassenanschrift.country
        initElementPatientAddress_Strassenanschrift_Country(snapshotComponent);

        // Patient.address:Postfach
        initElementPatientAddress_Postfach(snapshotComponent);

        // Patient.address:Postfach.type
        initElementPatientAddress_Postfach_Type(snapshotComponent);

        // Patient.address:Postfach.line
        initElementPatientAddress_Postfach_Line(snapshotComponent);

        // Patient.address:Postfach.line.extension:Postfach
        initElementPatientAddress_Postfach_Line_Ext_Postfach(snapshotComponent);

        // Patient.address:Postfach.line.extension:Postfach.value[x]
        initElementPatientAddress_Postfach_Line_Ext_Postfach_Value_X(snapshotComponent);

        // Patient.address:Postfach.line.extension:Postfach.value[x]:valueString
        initElementPatientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString(snapshotComponent);

        // Patient.address:Postfach.city
        initElementPatientAddress_Postfach_City(snapshotComponent);

        // Patient.address:Postfach.postalCode
        initElementPatientAddress_Postfach_PostalCode(snapshotComponent);

        // Patient.address:Postfach.country
        initElementPatientAddress_Postfach_Country(snapshotComponent);
    }

    protected void initElementPatientAddress_Postfach_Country(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_Country = snapshotComponent.addElement();

        patientAddress_Postfach_Country.setId("Patient.address:Postfach.country");
        patientAddress_Postfach_Country.getPathElement().setValue("Patient.address.country");
        patientAddress_Postfach_Country.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_PostalCode(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_PostalCode = snapshotComponent.addElement();

        patientAddress_Postfach_PostalCode.setId("Patient.address:Postfach.postalCode");
        patientAddress_Postfach_PostalCode.getPathElement().setValue("Patient.address.postalCode");
        patientAddress_Postfach_PostalCode.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_City(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_City = snapshotComponent.addElement();

        patientAddress_Postfach_City.setId("Patient.address:Postfach.city");
        patientAddress_Postfach_City.getPathElement().setValue("Patient.address.city");
        patientAddress_Postfach_City.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString =
                snapshotComponent.addElement();

        patientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString.setId(
                "Patient.address:Postfach.line.extension:Postfach.value[x]:valueString");
        patientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString.getPathElement().setValue(
                "Patient.address.line.extension.value[x]");
        patientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString.getSliceNameElement().setValue(
                "valueString");
        patientAddress_Postfach_Line_Ext_Postfach_Value_X_ValueString.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_Line_Ext_Postfach_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_Line_Ext_Postfach_Value_X =
                snapshotComponent.addElement();

        patientAddress_Postfach_Line_Ext_Postfach_Value_X.setId(
                "Patient.address:Postfach.line.extension:Postfach.value[x]");
        patientAddress_Postfach_Line_Ext_Postfach_Value_X.getPathElement().setValue(
                "Patient.address.line.extension.value[x]");
        patientAddress_Postfach_Line_Ext_Postfach_Value_X.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_Line_Ext_Postfach(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_Line_Ext_Postfach =
                snapshotComponent.addElement();

        patientAddress_Postfach_Line_Ext_Postfach.setId(
                "Patient.address:Postfach.line.extension:Postfach");
        patientAddress_Postfach_Line_Ext_Postfach.getPathElement().setValue(
                "Patient.address.line.extension");
        patientAddress_Postfach_Line_Ext_Postfach.getSliceNameElement().setValue("Postfach");
        patientAddress_Postfach_Line_Ext_Postfach.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_Line(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_Line = snapshotComponent.addElement();

        patientAddress_Postfach_Line.setId("Patient.address:Postfach.line");
        patientAddress_Postfach_Line.getPathElement().setValue("Patient.address.line");
        patientAddress_Postfach_Line.getMinElement().setValue(1);
        patientAddress_Postfach_Line.getMaxElement().setValue("1");
        patientAddress_Postfach_Line.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach_Type(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach_Type = snapshotComponent.addElement();

        patientAddress_Postfach_Type.setId("Patient.address:Postfach.type");
        patientAddress_Postfach_Type.getPathElement().setValue("Patient.address.type");
        patientAddress_Postfach_Type.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Postfach(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Postfach = snapshotComponent.addElement();

        patientAddress_Postfach.setId("Patient.address:Postfach");
        patientAddress_Postfach.getPathElement().setValue("Patient.address");
        patientAddress_Postfach.getSliceNameElement().setValue("Postfach");
        patientAddress_Postfach.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Country(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Country =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Country.setId(
                "Patient.address:Strassenanschrift.country");
        patientAddress_Strassenanschrift_Country.getPathElement().setValue(
                "Patient.address.country");
        patientAddress_Strassenanschrift_Country.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_PostalCode(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_PostalCode =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_PostalCode.setId(
                "Patient.address:Strassenanschrift.postalCode");
        patientAddress_Strassenanschrift_PostalCode.getPathElement().setValue(
                "Patient.address.postalCode");
        patientAddress_Strassenanschrift_PostalCode.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_City(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_City = snapshotComponent.addElement();

        patientAddress_Strassenanschrift_City.setId("Patient.address:Strassenanschrift.city");
        patientAddress_Strassenanschrift_City.getPathElement().setValue("Patient.address.city");
        patientAddress_Strassenanschrift_City.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString.setId(
                "Patient.address:Strassenanschrift.line.extension:Adresszusatz.value[x]:valueString");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString.getPathElement().setValue(
                "Patient.address.line.extension.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString.getSliceNameElement()
                .setValue("valueString");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X_ValueString.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_x =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_x.setId(
                "Patient.address:Strassenanschrift.line.extension:Adresszusatz.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_x.getPathElement().setValue(
                "Patient.address.line.extension.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Adresszusatz(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Adresszusatz =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz.setId(
                "Patient.address:Strassenanschrift.line.extension:Adresszusatz");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz.getPathElement().setValue(
                "Patient.address.line.extension");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz.getSliceNameElement()
                .setValue("Adresszusatz");
        patientAddress_Strassenanschrift_Line_Ext_Adresszusatz.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientBirthDate(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientBirthDate = snapshotComponent.addElement();

        patientBirthDate.setId("Patient.birthDate");
        patientBirthDate.getPathElement().setValue("Patient.birthDate");
        patientBirthDate.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode =
                snapshotComponent.addElement();

        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode.setId(
                "Patient.name:name.prefix.extension:prefix-qualifier.value[x]:valueCode");
        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode.getPathElement().setValue(
                "Patient.name.prefix.extension.value[x]");
        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode.getSliceNameElement()
                .setValue("valueCode");
        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X_ValueCode.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Prefix_Ext_Prefix_Qualifier_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_x =
                snapshotComponent.addElement();

        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_x.setId(
                "Patient.name:name.prefix.extension:prefix-qualifier.value[x]");
        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_x.getPathElement().setValue(
                "Patient.name.prefix.extension.value[x]");
        patientName_Name_Prefix_Ext_Prefix_Qualifier_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Prefix_Ext_Prefix_Qualifier(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Prefix_Ext_Prefix_Qualifier =
                snapshotComponent.addElement();

        patientName_Name_Prefix_Ext_Prefix_Qualifier.setId(
                "Patient.name:name.prefix.extension:prefix-qualifier");
        patientName_Name_Prefix_Ext_Prefix_Qualifier.getPathElement().setValue(
                "Patient.name.prefix.extension");
        patientName_Name_Prefix_Ext_Prefix_Qualifier.getSliceNameElement()
                .setValue("prefix-qualifier");
        patientName_Name_Prefix_Ext_Prefix_Qualifier.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Prefix(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Prefix =
                snapshotComponent.addElement();

        patientName_Name_Prefix.setId(
                "Patient.name:name.prefix");
        patientName_Name_Prefix.getPathElement().setValue("Patient.name.prefix");
        patientName_Name_Prefix.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Given(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Given =
                snapshotComponent.addElement();

        patientName_Name_Given.setId(
                "Patient.name:name.given");
        patientName_Name_Given.getPathElement().setValue("Patient.name.given");
        patientName_Name_Given.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString.setId(
                "Patient.name:name.family.extension:vorsatzwort.value[x]:valueString");
        patientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString.getPathElement().setValue(
                "Patient.name.family.extension.value[x]");
        patientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString.getSliceNameElement()
                .setValue("valueString");
        patientName_Name_Family_Ext_Vorsatzwort_Value_X_ValueString.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Vorsatzwort_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Vorsatzwort_Value_x =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Vorsatzwort_Value_x.setId(
                "Patient.name:name.family.extension:vorsatzwort.value[x]");
        patientName_Name_Family_Ext_Vorsatzwort_Value_x.getPathElement().setValue(
                "Patient.name.family.extension");
        patientName_Name_Family_Ext_Vorsatzwort_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Vorsatzwort(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Vorsatzwort =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Vorsatzwort.setId(
                "Patient.name:name.family.extension:vorsatzwort");
        patientName_Name_Family_Ext_Vorsatzwort.getPathElement().setValue(
                "Patient.name.family.extension");
        patientName_Name_Family_Ext_Vorsatzwort.getSliceNameElement()
                .setValue("vorsatzwort");
        patientName_Name_Family_Ext_Vorsatzwort.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Nachname_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Nachname_Value_X_ValueString =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Nachname_Value_X_ValueString.setId(
                "Patient.name:name.family.extension:nachname.value[x]:valueString");
        patientName_Name_Family_Ext_Nachname_Value_X_ValueString.getPathElement().setValue(
                "Patient.name.family.extension.value[x]");
        patientName_Name_Family_Ext_Nachname_Value_X_ValueString.getSliceNameElement()
                .setValue("valueString");
        patientName_Name_Family_Ext_Nachname_Value_X_ValueString.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Nachname_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Nachname_Value_x =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Nachname_Value_x.setId(
                "Patient.name:name.family.extension:nachname.value[x]");
        patientName_Name_Family_Ext_Nachname_Value_x.getPathElement().setValue(
                "Patient.name.family.extension.value[x]");
        patientName_Name_Family_Ext_Nachname_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Nachname(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Nachname =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Nachname.setId("Patient.name:name.family.extension:nachname");
        patientName_Name_Family_Ext_Nachname.getPathElement().setValue(
                "Patient.name.family.extension");
        patientName_Name_Family_Ext_Nachname.getSliceNameElement()
                .setValue("nachname");
        patientName_Name_Family_Ext_Nachname.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString.setId(
                "Patient.name:name.family.extension:namenszusatz.value[x]:valueString");
        patientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString.getPathElement().setValue(
                "Patient.name.family.extension.value[x]");
        patientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString.getSliceNameElement()
                .setValue("valueString");
        patientName_Name_Family_Ext_Namenszusatz_Value_X_ValueString.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Namenszusatz_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Namenszusatz_Value_x =
                snapshotComponent.addElement();

        patientName_Name_Family_Ext_Namenszusatz_Value_x.setId(
                "Patient.name:name.family.extension:namenszusatz.value[x]");
        patientName_Name_Family_Ext_Namenszusatz_Value_x.getPathElement().setValue(
                "Patient.name.family.extension.value[x]");
        patientName_Name_Family_Ext_Namenszusatz_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family_Ext_Namenszusatz(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family_Ext_Namenszusatz = snapshotComponent.addElement();

        patientName_Name_Family_Ext_Namenszusatz.setId(
                "Patient.name:name.family.extension:namenszusatz");
        patientName_Name_Family_Ext_Namenszusatz.getPathElement().setValue(
                "Patient.name.family.extension");
        patientName_Name_Family_Ext_Namenszusatz.getSliceNameElement().setValue("namenszusatz");
        patientName_Name_Family_Ext_Namenszusatz.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Family(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Family = snapshotComponent.addElement();

        patientName_Name_Family.setId("Patient.name:name.family");
        patientName_Name_Family.getPathElement().setValue("Patient.name.family");
        patientName_Name_Family.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name_Use(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name_Use = snapshotComponent.addElement();

        patientName_Name_Use.setId("Patient.name:name.use");
        patientName_Name_Use.getPathElement().setValue("Patient.name.use");
        patientName_Name_Use.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName_Name(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName_Name = snapshotComponent.addElement();

        patientName_Name.setId("Patient.name");
        patientName_Name.getPathElement().setValue("Patient.name");
        patientName_Name.getSliceNameElement().setValue("name");
        patientName_Name.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientName(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientName = snapshotComponent.addElement();

        patientName.setId("Patient.name");
        patientName.getPathElement().setValue("Patient.name");
        patientName.getMaxElement().setValue("1");
        patientName.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_KVK_Type_Coding_Code(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_KVK_Type_Coding_Code =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_KVK_Type_Coding_Code.setId(
                "Patient.identifier:versichertennummer_kvk.type.coding.code");
        patientIdentifierVersichertennummer_KVK_Type_Coding_Code.getPathElement().setValue(
                "Patient.identifier.type.coding.code");
        patientIdentifierVersichertennummer_KVK_Type_Coding_Code.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_KVK_Type_Coding_System(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_KVK_Type_Coding_System =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_KVK_Type_Coding_System.setId(
                "Patient.identifier:versichertennummer_kvk.type.coding.system");
        patientIdentifierVersichertennummer_KVK_Type_Coding_System.getPathElement().setValue(
                "Patient.identifier.type.coding.system");
        patientIdentifierVersichertennummer_KVK_Type_Coding_System.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_KVK_Type_Coding(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_KVK_Type_Coding =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_KVK_Type_Coding.setId(
                "Patient.identifier:versichertennummer_kvk.type.coding");
        patientIdentifierVersichertennummer_KVK_Type_Coding.getPathElement().setValue(
                "Patient.identifier.type.coding");
        patientIdentifierVersichertennummer_KVK_Type_Coding.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_KVK_Type(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_KVK_Type =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_KVK_Type.setId("Patient.identifier:versichertennummer_kvk");
        patientIdentifierVersichertennummer_KVK_Type.getPathElement().setValue("Patient.identifier.type");
        patientIdentifierVersichertennummer_KVK_Type.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_KVK(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_KVK =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_KVK.setId("Patient.identifier:versichertennummer_kvk");
        patientIdentifierVersichertennummer_KVK.getPathElement().setValue("Patient.identifier");
        patientIdentifierVersichertennummer_KVK.getSliceNameElement().setValue("versichertennummer_kvk");
        patientIdentifierVersichertennummer_KVK.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Assigner_Display(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Assigner_Display =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Assigner_Display.setId(
                "Patient.identifier:versichertennummer_pkv.assigner.display");

        patientIdentifierVersichertennummer_PKV_Assigner_Display.getPathElement().setValue(
                "Patient.identifier.assigner.display");

        patientIdentifierVersichertennummer_PKV_Assigner_Display.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Assigner(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Assigner =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Assigner.setId(
                "Patient.identifier:versichertennummer_pkv.assigner");

        patientIdentifierVersichertennummer_PKV_Assigner.getPathElement().setValue(
                "Patient.identifier.assigner");

        patientIdentifierVersichertennummer_PKV_Assigner.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Value(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Value =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Value.setId(
                "Patient.identifier:versichertennummer_pkv.value");

        patientIdentifierVersichertennummer_PKV_Value.getPathElement().setValue(
                "Patient.identifier.value");

        patientIdentifierVersichertennummer_PKV_Value.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_System(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_System =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_System.setId(
                "Patient.identifier:versichertennummer_pkv.system");

        patientIdentifierVersichertennummer_PKV_System.getPathElement().setValue(
                "Patient.identifier.system");

        patientIdentifierVersichertennummer_PKV_System.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Type_Coding_Code(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Type_Coding_Code =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Type_Coding_Code.setId(
                "Patient.identifier:versichertennummer_pkv.type.coding.code");

        patientIdentifierVersichertennummer_PKV_Type_Coding_Code.getPathElement().setValue(
                "Patient.identifier.type.coding.code");

        patientIdentifierVersichertennummer_PKV_Type_Coding_Code.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Type_Coding_System(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Type_Coding_System =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Type_Coding_System.setId(
                "Patient.identifier:versichertennummer_pkv.type.coding.system");

        patientIdentifierVersichertennummer_PKV_Type_Coding_System.getPathElement().setValue(
                "Patient.identifier.type.coding.system");

        patientIdentifierVersichertennummer_PKV_Type_Coding_System.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Type_Coding (
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Type_Coding =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Type_Coding.setId(
                "Patient.identifier:versichertennummer_pkv.type.coding");

        patientIdentifierVersichertennummer_PKV_Type_Coding.getPathElement().setValue(
                "Patient.identifier.type.coding");

        patientIdentifierVersichertennummer_PKV_Type_Coding.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV_Type(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV_Type =
                snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV_Type.setId(
                "Patient.identifier:versichertennummer_pkv.type");

        patientIdentifierVersichertennummer_PKV_Type.getPathElement().setValue(
                "Patient.identifier.type");

        patientIdentifierVersichertennummer_PKV_Type.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV_Value(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV_Value =
                snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV_Value.setId(
                "Patient.identifier:versichertenId_GKV.value");

        patientIdentifierVersichertenId_GKV_Value.getPathElement().setValue(
                "Patient.identifier.value");

        patientIdentifierVersichertenId_GKV_Value.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV_System(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV_System =
                snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV_System.setId(
                "Patient.identifier:versichertenId_GKV.system");

        patientIdentifierVersichertenId_GKV_System.getPathElement().setValue(
                "Patient.identifier.system");

        patientIdentifierVersichertenId_GKV_System.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertennummer_PKV (
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertennummer_PKV = snapshotComponent.addElement();

        patientIdentifierVersichertennummer_PKV.setId("Patient.identifier:versichertennummer_pkv");

        patientIdentifierVersichertennummer_PKV.getPathElement().setValue("Patient.identifier");
        patientIdentifierVersichertennummer_PKV.getSliceNameElement().setValue(
                "versichertennummer_pkv");
        patientIdentifierVersichertennummer_PKV.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientBirthDate_Ext_Data_Absent_Reason (
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientBirthDate_Ext_Data_Absent_Reason = snapshotComponent.addElement();

        patientBirthDate_Ext_Data_Absent_Reason.setId(
                "Patient.birthDate.extension:data-absent-reason");
        patientBirthDate_Ext_Data_Absent_Reason.getPathElement().setValue(
                "Patient.birthDate.extension");
        patientBirthDate_Ext_Data_Absent_Reason.getSliceNameElement()
                .setValue("data-absent-reason");
        patientBirthDate_Ext_Data_Absent_Reason.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientBirthDate_Ext_Data_Absent_Reason_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientBirthDate_Ext_Data_Absent_Reason_Value_x =
                snapshotComponent.addElement();

        patientBirthDate_Ext_Data_Absent_Reason_Value_x.setId(
                "Patient.birthDate.extension:data-absent-reason.value[x]");
        patientBirthDate_Ext_Data_Absent_Reason_Value_x.getPathElement().setValue(
                "Patient.birthDate.extension.value[x]");
        patientBirthDate_Ext_Data_Absent_Reason_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementpPatientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode =
                snapshotComponent.addElement();

        patientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode.setId(
                "Patient.birthDate.extension:data-absent-reason.value[x]:valueCode");
        patientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode.getPathElement().setValue(
                "Patient.birthDate.extension.value[x]");
        patientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode.getSliceNameElement()
                .setValue("valueCode");
        patientBirthDate_Ext_Data_Absent_Reason_Value_X_ValueCode.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress = snapshotComponent.addElement();

        patientAddress.setId("Patient.address");
        patientAddress.getPathElement().setValue("Patient.address");
        patientAddress.getMinElement().setValue(1);
        patientAddress.getMaxElement().setValue("1");
        patientAddress.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift = snapshotComponent.addElement();

        patientAddress_Strassenanschrift.setId("Patient.address:Strassenanschrift");
        patientAddress_Strassenanschrift.getPathElement().setValue("Patient.address");
        patientAddress_Strassenanschrift.getSliceNameElement().setValue("Strassenanschrift");
        patientAddress_Strassenanschrift.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Type(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Type = snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Type.setId("Patient.address:Strassenanschrift.type");
        patientAddress_Strassenanschrift_Type.getPathElement().setValue("Patient.address.type");
        patientAddress_Strassenanschrift_Type.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line = snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line.setId("Patient.address:Strassenanschrift.line");
        patientAddress_Strassenanschrift_Line.getPathElement().setValue("Patient.address.line");
        patientAddress_Strassenanschrift_Line.getMinElement().setValue(1);
        patientAddress_Strassenanschrift_Line.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Strasse(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Strasse =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Strasse.setId(
                "Patient.address:Strassenanschrift.line.extension:Strasse");
        patientAddress_Strassenanschrift_Line_Ext_Strasse.getPathElement().setValue(
                "Patient.address.line.extension");
        patientAddress_Strassenanschrift_Line_Ext_Strasse.getSliceNameElement().setValue("Strasse");
        patientAddress_Strassenanschrift_Line_Ext_Strasse.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_x =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_x.setId(
                "Patient.address:Strassenanschrift.line.extension:Strasse.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_x.getPathElement().setValue(
                "Patient.address.line.extension.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_x.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString.setId(
                "Patient.address:Strassenanschrift.line.extension:Strasse.value[x]:valueString");
        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString
                .getPathElement().setValue("Patient.address.line.extension.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString
                .getSliceNameElement().setValue("valueString");
        patientAddress_Strassenanschrift_Line_Ext_Strasse_Value_X_ValueString
                .getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Hausnummer(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Hausnummer =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Hausnummer.setId(
                "Patient.address:Strassenanschrift.line.extension:Hausnummer");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer
                .getPathElement().setValue("Patient.address.line.extension");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer
                .getSliceNameElement().setValue("Hausnummer");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer
                .getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_x =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_x.setId(
                "Patient.address:Strassenanschrift.line.extension:Hausnummer.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_x
                .getPathElement().setValue("Patient.address.line.extension.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_x
                .getMustSupportElement().setValue(true);
    }

    protected void initElementPatientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString =
                snapshotComponent.addElement();

        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString.setId(
                "Patient.address:Strassenanschrift.line.extension:Hausnummer.value[x]:valueString");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString
                .getPathElement().setValue("Patient.address.line.extension.value[x]");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString
                .getSliceNameElement().setValue("valueString");
        patientAddress_Strassenanschrift_Line_Ext_Hausnummer_Value_X_ValueString
                .getMustSupportElement().setValue(true);
    }

    protected void initElementPatientId(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdElementDefinition = snapshotComponent.addElement();

        patientIdElementDefinition.setId("Patient.id");

        patientIdElementDefinition.getPathElement().setValue("Patient.id");
        patientIdElementDefinition.getMinElement().setValue(1);
        patientIdElementDefinition.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientMeta(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientMetaElementDefinition = snapshotComponent.addElement();

        patientMetaElementDefinition.setId("Patient.meta");
        patientMetaElementDefinition.getPathElement().setValue("Patient.meta");
        patientMetaElementDefinition.getMinElement().setValue(1);
        patientMetaElementDefinition.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientMetaProfile(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientMetaProfile = snapshotComponent.addElement();

        patientMetaProfile.setId("Patient.meta.profile");
        patientMetaProfile.getPathElement().setValue("Patient.meta.profile");
        patientMetaProfile.getMinElement().setValue(1);
        patientMetaProfile.getMaxElement().setValue("1");
//        patientMetaProfile.getFixed().addExtension("");
        patientMetaProfile.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifier(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifier = snapshotComponent.addElement();

        patientIdentifier.setId("Patient.identifier");

        patientIdentifier.getPathElement().setValue("Patient.identifier");

        patientIdentifier.getMaxElement().setValue("1");
        patientIdentifier.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV = snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV.setId("Patient.identifier:versichertenId_GKV");

        patientIdentifierVersichertenId_GKV.getPathElement().setValue("Patient.identifier");
        patientIdentifierVersichertenId_GKV.getSliceNameElement().setValue("versichertenId_GKV");
        patientIdentifierVersichertenId_GKV.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV_Type(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV_Type = snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV_Type.setId("Patient.identifier:versichertenId_GKV.type");

        patientIdentifierVersichertenId_GKV_Type.getPathElement().setValue("Patient.identifier.type");
        patientIdentifierVersichertenId_GKV_Type.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV_Type_Coding(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV_Type_Coding =
                snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV_Type_Coding.setId(
                "Patient.identifier:versichertenId_GKV.type.coding");

        patientIdentifierVersichertenId_GKV_Type_Coding.getPathElement().setValue(
                "Patient.identifier.type.coding");
        patientIdentifierVersichertenId_GKV_Type_Coding.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV_Type_Coding_System(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV_Type_Coding_System =
                snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV_Type_Coding_System.setId(
                "Patient.identifier:versichertenId_GKV.type.coding.system");

        patientIdentifierVersichertenId_GKV_Type_Coding_System.getPathElement().setValue(
                "Patient.identifier.type.coding.system");
        patientIdentifierVersichertenId_GKV_Type_Coding_System.getMustSupportElement().setValue(true);
    }

    protected void initElementPatientIdentifierVersichertenId_GKV_Type_Coding_Code(
            StructureDefinition.StructureDefinitionSnapshotComponent snapshotComponent) {
        ElementDefinition patientIdentifierVersichertenId_GKV_Type_Coding_Code =
                snapshotComponent.addElement();

        patientIdentifierVersichertenId_GKV_Type_Coding_Code.setId(
                "Patient.identifier:versichertenId_GKV.type.coding.code");

        patientIdentifierVersichertenId_GKV_Type_Coding_Code.getPathElement().setValue(
                "Patient.identifier.type.coding.code");

        patientIdentifierVersichertenId_GKV_Type_Coding_Code.getMustSupportElement().setValue(true);
    }
}