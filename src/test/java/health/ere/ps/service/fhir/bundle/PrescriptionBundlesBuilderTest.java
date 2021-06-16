package health.ere.ps.service.fhir.bundle;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.ValidationResult;
import health.ere.ps.model.muster16.MedicationString;
import health.ere.ps.model.muster16.Muster16PrescriptionForm;
import health.ere.ps.validation.fhir.bundle.PrescriptionBundleValidator;
import io.quarkus.test.junit.QuarkusTest;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Coverage;
import org.hl7.fhir.r4.model.Patient;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PrescriptionBundlesBuilderTest {
    @Inject
    Logger logger;

    private PrescriptionBundleValidator prescriptionBundleValidator;
    private PrescriptionBundlesBuilder prescriptionBundlesBuilder;

    public static Muster16PrescriptionForm getMuster16PrescriptionFormForTests() {
        Muster16PrescriptionForm muster16PrescriptionForm;
        muster16PrescriptionForm = new Muster16PrescriptionForm();

        muster16PrescriptionForm.setClinicId("BS12345678");

        muster16PrescriptionForm.setPrescriptionDate("05.04.2021");
        MedicationString medicationString = new MedicationString("Amoxicillin 1000mg N2", null, null, "3x täglich alle 8 Std", null, "2394428");

        muster16PrescriptionForm.setPrescriptionList(Collections.singletonList(medicationString));

        muster16PrescriptionForm.setDoctorId("LANR1234");

        muster16PrescriptionForm.setInsuranceCompany("Test Insurance Company, Gmbh");

        muster16PrescriptionForm.setPatientDateOfBirth("16.07.1986");
        muster16PrescriptionForm.setPatientNamePrefix(List.of("Dr."));
        muster16PrescriptionForm.setPatientFirstName("John");
        muster16PrescriptionForm.setPatientLastName("Doe");
        muster16PrescriptionForm.setPatientStreetName("Droysenstr.");
        muster16PrescriptionForm.setPatientStreetNumber("7");
        muster16PrescriptionForm.setPatientZipCode("10629");
        muster16PrescriptionForm.setPatientCity("Berlin");
        muster16PrescriptionForm.setPatientInsuranceId("M310119800");

        muster16PrescriptionForm.setDoctorNamePrefix("Dr.");
        muster16PrescriptionForm.setDoctorFirstName("Testarzt");
        muster16PrescriptionForm.setDoctorLastName("E-Rezept");
        muster16PrescriptionForm.setDoctorPhone("123456789");

        muster16PrescriptionForm.setDoctorStreetName("Doc Droysenstr.");
        muster16PrescriptionForm.setDoctorStreetNumber("7a");
        muster16PrescriptionForm.setDoctorZipCode("10630");
        muster16PrescriptionForm.setDoctorCity("Berlinn");

        muster16PrescriptionForm.setDoctorPhone("030/123456");

        muster16PrescriptionForm.setInsuranceCompanyId("100038825");
        muster16PrescriptionForm.setWithPayment(true);

        return muster16PrescriptionForm;
    }

    @BeforeEach
    public void initialize() {
        prescriptionBundlesBuilder = new PrescriptionBundlesBuilder(getMuster16PrescriptionFormForTests());
        prescriptionBundleValidator = new PrescriptionBundleValidator();
    }

    @Test
    public void test_Successful_Creation_of_FHIR_EPrescription_Bundle_From_Muster16_Model_Object()
            throws ParseException {

        List<Bundle> fhirEPrescriptionBundles = prescriptionBundlesBuilder.createBundles();

        // Expecting the creation of 7 resources
        // 1. composition resource
        // 2. medication request resource
        // 3. medication resource.
        // 4. patient resource.
        // 5. practitioner resource.
        // 6. organization resource.
        // 7. coverage resource.
        fhirEPrescriptionBundles.forEach(bundle -> assertEquals(7, bundle.getEntry().size()));
        assertEquals(1, fhirEPrescriptionBundles.size());
    }

    @Test
    public void BundleBuilder_createsCorrectNumberOfBundles_givenThreeMedications() throws ParseException {
        // GIVEN
        Muster16PrescriptionForm muster16PrescriptionForm = getMuster16PrescriptionFormForTests();
        muster16PrescriptionForm.setPrescriptionList(List.of(
                new MedicationString("test", "test", "test", "test", "test", "test"),
                new MedicationString("test", "test", "test", "test", "test", "test"),
                new MedicationString("test", "test", "test", "test", "test", "test")));

        prescriptionBundlesBuilder = new PrescriptionBundlesBuilder(muster16PrescriptionForm);

        // WHEN
        List<Bundle> fhirEPrescriptionBundles = prescriptionBundlesBuilder.createBundles();

        // THEN
        assertEquals(3, fhirEPrescriptionBundles.size());
    }

    @Test
    public void test_Successful_XML_Serialization_Of_An_FHIR_EPrescription_Bundle_Object()
            throws ParseException {
        FhirContext ctx = FhirContext.forR4();

        IParser parser = ctx.newXmlParser();

        List<Bundle> fhirEPrescriptionBundles = prescriptionBundlesBuilder.createBundles();

        fhirEPrescriptionBundles.forEach(bundle -> {
            bundle.setId("sample-id-from-gematik-ti-123456");
            parser.setPrettyPrint(true);

            String serialized = parser.encodeResourceToString(bundle);

            logger.info(serialized);
        });
    }

    @Test
    public void test_Successful_JSON_Serialization_Of_An_FHIR_EPrescription_Bundle_Object()
            throws ParseException {
        FhirContext ctx = FhirContext.forR4();

        IParser parser = ctx.newJsonParser();

        List<Bundle> fhirEPrescriptionBundles = prescriptionBundlesBuilder.createBundles();

        fhirEPrescriptionBundles.forEach(bundle -> {
            bundle.setId("sample-id-from-gematik-ti-123456");
            parser.setPrettyPrint(true);

            String serialized = parser.encodeResourceToString(bundle);

            logger.info(serialized);
        });
    }

    @Disabled
    @Test
    public void test_Successful_JSON_To_Bundle_Object_Conversion() {
        FhirContext ctx = FhirContext.forR4();
        IParser jsonParser = ctx.newJsonParser();
        String densSignjson = " {\"resourceType\":\"Bundle\"," +
                "\"id\":\"e6baf9c0-5d88-4b28-b15d-1c3a2c3f3d19\",\"meta\":{\"lastUpdated\":\"2021-06-16T13:05:38.948-04:00\",\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Bundle|1.0.1\"]},\"type\":\"document\",\"timestamp\":\"2021-06-16T13:05:38.948-04:00\",\"entry\":[{\"fullUrl\":\"http://pvs.praxis.local/fhir/Medication/9d8c5ab9-73b8-4165-9f3a-9eb354ea1f88\",\"resource\":{\"resourceType\":\"Medication\",\"id\":\"9d8c5ab9-73b8-4165-9f3a-9eb354ea1f88\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Medication_PZN|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Category\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Medication_Category\",\"code\":\"00\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Vaccine\",\"valueBoolean\":false},{\"url\":\"http://fhir.de/StructureDefinition/normgroesse\",\"valueCode\":\"N1\"}],\"code\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/ifa/pzn\",\"code\":\"00027950\"}],\"text\":\"Ibuprofen 600mg\"},\"form\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DARREICHUNGSFORM\",\"code\":\"FLE\"}]}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/MedicationRequest/028df042-2321-410c-9fa0-148af5d2b909\",\"resource\":{\"resourceType\":\"MedicationRequest\",\"id\":\"028df042-2321-410c-9fa0-148af5d2b909\",\"meta\":{\"lastUpdated\":\"2021-06-16T13:05:38.948-04:00\",\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Prescription|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_StatusCoPayment\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_StatusCoPayment\",\"code\":\"1\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_EmergencyServicesFee\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_BVG\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Multiple_Prescription\",\"extension\":[{\"url\":\"Kennzeichen\",\"valueBoolean\":false}]}],\"status\":\"active\",\"intent\":\"order\",\"medicationReference\":{\"reference\":\"Medication/9d8c5ab9-73b8-4165-9f3a-9eb354ea1f88\"},\"subject\":{\"reference\":\"Patient/\"},\"requester\":{\"reference\":\"Practitioner/30000000\"},\"insurance\":[{\"reference\":\"Coverage/\"}],\"dosageInstruction\":[{\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_DosageFlag\",\"valueBoolean\":true}],\"text\":\"1-1-1\"}],\"dispenseRequest\":{\"quantity\":{\"value\":1,\"system\":\"http://unitsofmeasure.org\",\"code\":\"{Package}\"}},\"substitution\":{\"allowedBoolean\":true}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Composition/ba4fc629-93ce-4670-b47a-b0596bc0aaa6\",\"resource\":{\"resourceType\":\"Composition\",\"id\":\"ba4fc629-93ce-4670-b47a-b0596bc0aaa6\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Composition|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_FOR_Legal_basis\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_STATUSKENNZEICHEN\",\"code\":\"04\"}}],\"status\":\"final\",\"type\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_FORMULAR_ART\",\"code\":\"e16A\"}]},\"subject\":{\"reference\":\"Patient/\"},\"date\":\"2021-06-16T13:05:38-04:00\",\"author\":[{\"reference\":\"Practitioner/30000000\",\"type\":\"Practitioner\"},{\"type\":\"Device\",\"identifier\":{\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_FOR_Pruefnummer\",\"value\":\"123456\"}}],\"title\":\"elektronische Arzneimittelverordnung\",\"attester\":[{\"mode\":\"legal\",\"party\":{\"reference\":\"Practitioner/30000000\"}}],\"custodian\":{\"reference\":\"Organization/30000000\"},\"section\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Prescription\"}]},\"entry\":[{\"reference\":\"MedicationRequest/028df042-2321-410c-9fa0-148af5d2b909\"}]},{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Coverage\"}]},\"entry\":[{\"reference\":\"Coverage/\"}]}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Patient/null\",\"resource\":{\"resourceType\":\"Patient\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/identifier-type-de-basis\",\"code\":\"GKV\"}]},\"system\":\"http://fhir.de/NamingSystem/gkv/kvid-10\"}],\"name\":[{\"use\":\"official\",\"family\":\"Heckner\",\"given\":[\"Markus\"],\"prefix\":[\"Dr.\"]}],\"address\":[{\"type\":\"both\",\"line\":[\"Berliner Str. 12\"],\"city\":\"Teltow\",\"postalCode\":\"14513\",\"country\":\"D\",\"_line\":[{\"extension\":null}]}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Practitioner/30000000\",\"resource\":{\"resourceType\":\"Practitioner\",\"id\":\"30000000\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Practitioner|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"LANR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_ANR\",\"value\":\"30000000\"}],\"name\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier\",\"valueString\":\"AC\"}],\"use\":\"official\",\"family\":\"Doctor Last Name\",\"given\":[\"Doctor First Name\"]}],\"qualification\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_FOR_Qualification_Type\",\"code\":\"00\",\"display\":\"Arzt-Hausarzt\"}]}},{\"code\":{\"text\":\"Arzt-Hausarzt\"}}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Organization/30000000\",\"resource\":{\"resourceType\":\"Organization\",\"id\":\"30000000\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Organization|1.0.3\",\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Coverage|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"BSNR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_BSNR\",\"value\":\"30000000\"}],\"name\":\"null Doctor First Name Doctor Last Name\",\"telecom\":[{\"system\":\"phone\",\"value\":\"030/123456789\"}],\"address\":[{\"type\":\"both\",\"line\":[\"Doctor Street Name Doctor Street Number\"],\"city\":\"Doctor City\",\"postalCode\":\"012345\",\"country\":\"D\"}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Coverage/null\",\"resource\":{\"resourceType\":\"Coverage\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Coverage|1.0.3\"]},\"extension\":[{\"url\":\"http://fhir.de/StructureDefinition/gkv/besondere-personengruppe\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_PERSONENGRUPPE\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/dmp-kennzeichen\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DMP\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/wop\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_ITA_WOP\",\"code\":\"72\"}},{\"url\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_VERSICHERTENSTATUS\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_ITA_WOP\",\"code\":\"3\"}}],\"status\":\"active\",\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/versicherungsart-de-basis\",\"code\":\"GKV\"}]},\"beneficiary\":{\"reference\":\"Patient/\"},\"payor\":[{\"identifier\":{\"system\":\"http://fhir.de/NamingSystem/arge-ik/iknr\"},\"display\":\"DENS GmbH\"}]}}]}";
        String arbitraryJson = "{\"resourceType\":\"Bundle\",\"id\":\"f70585e0-82f9-4d3d-b248-94504ccf6a66\",\"meta\":{\"lastUpdated\":\"2021-04-06T08:30:00Z\",\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Bundle|1.0.1\"]},\"identifier\":{\"system\":\"https://gematik.de/fhir/NamingSystem/PrescriptionID\",\"value\":\"160.100.000.000.016.91\"},\"type\":\"document\",\"timestamp\":\"2021-04-06T08:30:00Z\",\"entry\":[{\"fullUrl\":\"http://pvs.praxis.local/fhir/Composition/1868bb7c-c1a6-48a6-a327-05ff8d24c64a\",\"resource\":{\"resourceType\":\"Composition\",\"id\":\"1868bb7c-c1a6-48a6-a327-05ff8d24c64a\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Composition|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_FOR_Legal_basis\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_STATUSKENNZEICHEN\",\"code\":\"00\"}}],\"status\":\"final\",\"type\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_FORMULAR_ART\",\"code\":\"e16A\"}]},\"subject\":{\"reference\":\"Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\"},\"date\":\"2021-04-06T08:00:00Z\",\"author\":[{\"reference\":\"Practitioner/667ffd79-42a3-4002-b7ca-6b9098f20ccb\",\"type\":\"Practitioner\"},{\"type\":\"Device\",\"identifier\":{\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_FOR_Pruefnummer\",\"value\":\"Y/410/2107/36/999\"}}],\"title\":\"elektronische Arzneimittelverordnung\",\"custodian\":{\"reference\":\"Organization/5d3f4ac0-2b44-4d48-b363-e63efa72973b\"},\"section\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Prescription\"}]},\"entry\":[{\"reference\":\"MedicationRequest/76b5767d-55a5-4233-8f85-e15a24a5193a\"}]},{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Coverage\"}]},\"entry\":[{\"reference\":\"Coverage/da80211e-61ee-458e-a651-87370b6ec30c\"}]}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/MedicationRequest/76b5767d-55a5-4233-8f85-e15a24a5193a\",\"resource\":{\"resourceType\":\"MedicationRequest\",\"id\":\"76b5767d-55a5-4233-8f85-e15a24a5193a\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Prescription|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_StatusCoPayment\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_StatusCoPayment\",\"code\":\"0\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_EmergencyServicesFee\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_BVG\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Multiple_Prescription\",\"extension\":[{\"url\":\"Kennzeichen\",\"valueBoolean\":true},{\"url\":\"Nummerierung\",\"valueRatio\":{\"numerator\":{\"value\":3},\"denominator\":{\"value\":4}}},{\"url\":\"Zeitraum\",\"valuePeriod\":{\"start\":\"2021-09-15\",\"end\":\"2021-12-31\"}}]}],\"status\":\"active\",\"intent\":\"order\",\"medicationReference\":{\"reference\":\"Medication/07c10a67-2ece-4d5d-9394-633e07c9656d\"},\"subject\":{\"reference\":\"Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\"},\"authoredOn\":\"2021-04-01\",\"requester\":{\"reference\":\"Practitioner/667ffd79-42a3-4002-b7ca-6b9098f20ccb\"},\"insurance\":[{\"reference\":\"Coverage/da80211e-61ee-458e-a651-87370b6ec30c\"}],\"dosageInstruction\":[{\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_DosageFlag\",\"valueBoolean\":false}]}],\"dispenseRequest\":{\"quantity\":{\"value\":1,\"system\":\"http://unitsofmeasure.org\",\"code\":\"{Package}\"}},\"substitution\":{\"allowedBoolean\":false}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Medication/07c10a67-2ece-4d5d-9394-633e07c9656d\",\"resource\":{\"resourceType\":\"Medication\",\"id\":\"07c10a67-2ece-4d5d-9394-633e07c9656d\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Medication_PZN|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Category\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Medication_Category\",\"code\":\"00\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Vaccine\",\"valueBoolean\":false},{\"url\":\"http://fhir.de/StructureDefinition/normgroesse\",\"valueCode\":\"N3\"}],\"code\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/ifa/pzn\",\"code\":\"02532741\"}],\"text\":\"L-Thyroxin Henning 75 100 Tbl. N3\"},\"form\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DARREICHUNGSFORM\",\"code\":\"TAB\"}]}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\",\"resource\":{\"resourceType\":\"Patient\",\"id\":\"ce4104af-b86b-4664-afee-1b5fc3ac8acf\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/identifier-type-de-basis\",\"code\":\"GKV\"}]},\"system\":\"http://fhir.de/NamingSystem/gkv/kvid-10\",\"value\":\"K030182229\"}],\"name\":[{\"use\":\"official\",\"family\":\"Kluge\",\"_family\":{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/humanname-own-name\",\"valueString\":\"Kluge\"}]},\"given\":[\"Eva\"],\"prefix\":[\"Prof. Dr. Dr. med\"],\"_prefix\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier\",\"valueCode\":\"AC\"}]}]}],\"birthDate\":\"1982-01-03\",\"address\":[{\"type\":\"both\",\"line\":[\"Pflasterhofweg 111B\"],\"_line\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-houseNumber\",\"valueString\":\"111B\"},{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-streetName\",\"valueString\":\"Pflasterhofweg\"}]}],\"city\":\"Köln\",\"postalCode\":\"50999\",\"country\":\"D\"}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Practitioner/667ffd79-42a3-4002-b7ca-6b9098f20ccb\",\"resource\":{\"resourceType\":\"Practitioner\",\"id\":\"667ffd79-42a3-4002-b7ca-6b9098f20ccb\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Practitioner|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"LANR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_ANR\",\"value\":\"987654423\"}],\"name\":[{\"use\":\"official\",\"family\":\"Schneider\",\"_family\":{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/humanname-own-name\",\"valueString\":\"Schneider\"}]},\"given\":[\"Emma\"],\"prefix\":[\"Dr. med.\"],\"_prefix\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier\",\"valueCode\":\"AC\"}]}]}],\"qualification\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_FOR_Qualification_Type\",\"code\":\"00\"}]}},{\"code\":{\"text\":\"Fachärztin für Innere Medizin\"}}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Organization/5d3f4ac0-2b44-4d48-b363-e63efa72973b\",\"resource\":{\"resourceType\":\"Organization\",\"id\":\"5d3f4ac0-2b44-4d48-b363-e63efa72973b\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Organization|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"BSNR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_BSNR\",\"value\":\"721111100\"}],\"name\":\"MVZ\",\"telecom\":[{\"system\":\"phone\",\"value\":\"0301234567\"},{\"system\":\"fax\",\"value\":\"030123456789\"},{\"system\":\"email\",\"value\":\"mvz@e-mail.de\"}],\"address\":[{\"type\":\"both\",\"line\":[\"Herbert-Lewin-Platz 2\"],\"_line\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-houseNumber\",\"valueString\":\"2\"},{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-streetName\",\"valueString\":\"Herbert-Lewin-Platz\"}]}],\"city\":\"Berlin\",\"postalCode\":\"10623\",\"country\":\"D\"}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Coverage/da80211e-61ee-458e-a651-87370b6ec30c\",\"resource\":{\"resourceType\":\"Coverage\",\"id\":\"da80211e-61ee-458e-a651-87370b6ec30c\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Coverage|1.0.3\"]},\"extension\":[{\"url\":\"http://fhir.de/StructureDefinition/gkv/besondere-personengruppe\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_PERSONENGRUPPE\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/dmp-kennzeichen\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DMP\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/wop\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_ITA_WOP\",\"code\":\"38\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/versichertenart\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_VERSICHERTENSTATUS\",\"code\":\"3\"}}],\"status\":\"active\",\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/versicherungsart-de-basis\",\"code\":\"GKV\"}]},\"beneficiary\":{\"reference\":\"Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\"},\"payor\":[{\"identifier\":{\"system\":\"http://fhir.de/NamingSystem/arge-ik/iknr\",\"value\":\"109777509\"},\"display\":\"Techniker-Krankenkasse\"}]}}]}";
        Bundle bundle = jsonParser.parseResource(Bundle.class, arbitraryJson);
    }

    @Test
    public void test_Successful_Validation_Of_An_FHIR_KBV_Bundle_Resource() {
        Patient patientResource = prescriptionBundlesBuilder.createPatientResource();
        String arbitrarySampleJson = "{\"resourceType\":\"Bundle\",\"id\":\"f70585e0-82f9-4d3d-b248-94504ccf6a66\",\"meta\":{\"lastUpdated\":\"2021-04-06T08:30:00Z\",\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Bundle|1.0.1\"]},\"identifier\":{\"system\":\"https://gematik.de/fhir/NamingSystem/PrescriptionID\",\"value\":\"160.100.000.000.016.91\"},\"type\":\"document\",\"timestamp\":\"2021-04-06T08:30:00Z\",\"entry\":[{\"fullUrl\":\"http://pvs.praxis.local/fhir/Composition/1868bb7c-c1a6-48a6-a327-05ff8d24c64a\",\"resource\":{\"resourceType\":\"Composition\",\"id\":\"1868bb7c-c1a6-48a6-a327-05ff8d24c64a\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Composition|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_FOR_Legal_basis\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_STATUSKENNZEICHEN\",\"code\":\"00\"}}],\"status\":\"final\",\"type\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_FORMULAR_ART\",\"code\":\"e16A\"}]},\"subject\":{\"reference\":\"Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\"},\"date\":\"2021-04-06T08:00:00Z\",\"author\":[{\"reference\":\"Practitioner/667ffd79-42a3-4002-b7ca-6b9098f20ccb\",\"type\":\"Practitioner\"},{\"type\":\"Device\",\"identifier\":{\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_FOR_Pruefnummer\",\"value\":\"Y/410/2107/36/999\"}}],\"title\":\"elektronische Arzneimittelverordnung\",\"custodian\":{\"reference\":\"Organization/5d3f4ac0-2b44-4d48-b363-e63efa72973b\"},\"section\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Prescription\"}]},\"entry\":[{\"reference\":\"MedicationRequest/76b5767d-55a5-4233-8f85-e15a24a5193a\"}]},{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Coverage\"}]},\"entry\":[{\"reference\":\"Coverage/da80211e-61ee-458e-a651-87370b6ec30c\"}]}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/MedicationRequest/76b5767d-55a5-4233-8f85-e15a24a5193a\",\"resource\":{\"resourceType\":\"MedicationRequest\",\"id\":\"76b5767d-55a5-4233-8f85-e15a24a5193a\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Prescription|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_StatusCoPayment\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_StatusCoPayment\",\"code\":\"0\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_EmergencyServicesFee\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_BVG\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Multiple_Prescription\",\"extension\":[{\"url\":\"Kennzeichen\",\"valueBoolean\":true},{\"url\":\"Nummerierung\",\"valueRatio\":{\"numerator\":{\"value\":3},\"denominator\":{\"value\":4}}},{\"url\":\"Zeitraum\",\"valuePeriod\":{\"start\":\"2021-09-15\",\"end\":\"2021-12-31\"}}]}],\"status\":\"active\",\"intent\":\"order\",\"medicationReference\":{\"reference\":\"Medication/07c10a67-2ece-4d5d-9394-633e07c9656d\"},\"subject\":{\"reference\":\"Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\"},\"authoredOn\":\"2021-04-01\",\"requester\":{\"reference\":\"Practitioner/667ffd79-42a3-4002-b7ca-6b9098f20ccb\"},\"insurance\":[{\"reference\":\"Coverage/da80211e-61ee-458e-a651-87370b6ec30c\"}],\"dosageInstruction\":[{\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_DosageFlag\",\"valueBoolean\":false}]}],\"dispenseRequest\":{\"quantity\":{\"value\":1,\"system\":\"http://unitsofmeasure.org\",\"code\":\"{Package}\"}},\"substitution\":{\"allowedBoolean\":false}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Medication/07c10a67-2ece-4d5d-9394-633e07c9656d\",\"resource\":{\"resourceType\":\"Medication\",\"id\":\"07c10a67-2ece-4d5d-9394-633e07c9656d\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Medication_PZN|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Category\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Medication_Category\",\"code\":\"00\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Vaccine\",\"valueBoolean\":false},{\"url\":\"http://fhir.de/StructureDefinition/normgroesse\",\"valueCode\":\"N3\"}],\"code\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/ifa/pzn\",\"code\":\"02532741\"}],\"text\":\"L-Thyroxin Henning 75 100 Tbl. N3\"},\"form\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DARREICHUNGSFORM\",\"code\":\"TAB\"}]}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\",\"resource\":{\"resourceType\":\"Patient\",\"id\":\"ce4104af-b86b-4664-afee-1b5fc3ac8acf\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/identifier-type-de-basis\",\"code\":\"GKV\"}]},\"system\":\"http://fhir.de/NamingSystem/gkv/kvid-10\",\"value\":\"K030182229\"}],\"name\":[{\"use\":\"official\",\"family\":\"Kluge\",\"_family\":{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/humanname-own-name\",\"valueString\":\"Kluge\"}]},\"given\":[\"Eva\"],\"prefix\":[\"Prof. Dr. Dr. med\"],\"_prefix\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier\",\"valueCode\":\"AC\"}]}]}],\"birthDate\":\"1982-01-03\",\"address\":[{\"type\":\"both\",\"line\":[\"Pflasterhofweg 111B\"],\"_line\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-houseNumber\",\"valueString\":\"111B\"},{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-streetName\",\"valueString\":\"Pflasterhofweg\"}]}],\"city\":\"Köln\",\"postalCode\":\"50999\",\"country\":\"D\"}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Practitioner/667ffd79-42a3-4002-b7ca-6b9098f20ccb\",\"resource\":{\"resourceType\":\"Practitioner\",\"id\":\"667ffd79-42a3-4002-b7ca-6b9098f20ccb\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Practitioner|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"LANR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_ANR\",\"value\":\"987654423\"}],\"name\":[{\"use\":\"official\",\"family\":\"Schneider\",\"_family\":{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/humanname-own-name\",\"valueString\":\"Schneider\"}]},\"given\":[\"Emma\"],\"prefix\":[\"Dr. med.\"],\"_prefix\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier\",\"valueCode\":\"AC\"}]}]}],\"qualification\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_FOR_Qualification_Type\",\"code\":\"00\"}]}},{\"code\":{\"text\":\"Fachärztin für Innere Medizin\"}}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Organization/5d3f4ac0-2b44-4d48-b363-e63efa72973b\",\"resource\":{\"resourceType\":\"Organization\",\"id\":\"5d3f4ac0-2b44-4d48-b363-e63efa72973b\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Organization|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"BSNR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_BSNR\",\"value\":\"721111100\"}],\"name\":\"MVZ\",\"telecom\":[{\"system\":\"phone\",\"value\":\"0301234567\"},{\"system\":\"fax\",\"value\":\"030123456789\"},{\"system\":\"email\",\"value\":\"mvz@e-mail.de\"}],\"address\":[{\"type\":\"both\",\"line\":[\"Herbert-Lewin-Platz 2\"],\"_line\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-houseNumber\",\"valueString\":\"2\"},{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-streetName\",\"valueString\":\"Herbert-Lewin-Platz\"}]}],\"city\":\"Berlin\",\"postalCode\":\"10623\",\"country\":\"D\"}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Coverage/da80211e-61ee-458e-a651-87370b6ec30c\",\"resource\":{\"resourceType\":\"Coverage\",\"id\":\"da80211e-61ee-458e-a651-87370b6ec30c\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Coverage|1.0.3\"]},\"extension\":[{\"url\":\"http://fhir.de/StructureDefinition/gkv/besondere-personengruppe\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_PERSONENGRUPPE\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/dmp-kennzeichen\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DMP\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/wop\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_ITA_WOP\",\"code\":\"38\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/versichertenart\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_VERSICHERTENSTATUS\",\"code\":\"3\"}}],\"status\":\"active\",\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/versicherungsart-de-basis\",\"code\":\"GKV\"}]},\"beneficiary\":{\"reference\":\"Patient/ce4104af-b86b-4664-afee-1b5fc3ac8acf\"},\"payor\":[{\"identifier\":{\"system\":\"http://fhir.de/NamingSystem/arge-ik/iknr\",\"value\":\"109777509\"},\"display\":\"Techniker-Krankenkasse\"}]}}]}";

        String densSampleSignJson = "{\"resourceType\":\"Bundle\"," +
                "\"id\":\"e6baf9c0-5d88-4b28-b15d-1c3a2c3f3d19\",\"meta\":{\"lastUpdated\":\"2021-06-16T13:05:38.948-04:00\",\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Bundle|1.0.1\"]},\"type\":\"document\",\"timestamp\":\"2021-06-16T13:05:38.948-04:00\",\"entry\":[{\"fullUrl\":\"http://pvs.praxis.local/fhir/Medication/9d8c5ab9-73b8-4165-9f3a-9eb354ea1f88\",\"resource\":{\"resourceType\":\"Medication\",\"id\":\"9d8c5ab9-73b8-4165-9f3a-9eb354ea1f88\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Medication_PZN|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Category\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Medication_Category\",\"code\":\"00\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Medication_Vaccine\",\"valueBoolean\":false},{\"url\":\"http://fhir.de/StructureDefinition/normgroesse\",\"valueCode\":\"N1\"}],\"code\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/ifa/pzn\",\"code\":\"00027950\"}],\"text\":\"Ibuprofen 600mg\"},\"form\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DARREICHUNGSFORM\",\"code\":\"FLE\"}]}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/MedicationRequest/028df042-2321-410c-9fa0-148af5d2b909\",\"resource\":{\"resourceType\":\"MedicationRequest\",\"id\":\"028df042-2321-410c-9fa0-148af5d2b909\",\"meta\":{\"lastUpdated\":\"2021-06-16T13:05:38.948-04:00\",\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Prescription|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_StatusCoPayment\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_StatusCoPayment\",\"code\":\"1\"}},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_EmergencyServicesFee\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_BVG\",\"valueBoolean\":false},{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_Multiple_Prescription\",\"extension\":[{\"url\":\"Kennzeichen\",\"valueBoolean\":false}]}],\"status\":\"active\",\"intent\":\"order\",\"medicationReference\":{\"reference\":\"Medication/9d8c5ab9-73b8-4165-9f3a-9eb354ea1f88\"},\"subject\":{\"reference\":\"Patient/\"},\"requester\":{\"reference\":\"Practitioner/30000000\"},\"insurance\":[{\"reference\":\"Coverage/\"}],\"dosageInstruction\":[{\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_ERP_DosageFlag\",\"valueBoolean\":true}],\"text\":\"1-1-1\"}],\"dispenseRequest\":{\"quantity\":{\"value\":1,\"system\":\"http://unitsofmeasure.org\",\"code\":\"{Package}\"}},\"substitution\":{\"allowedBoolean\":true}}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Composition/ba4fc629-93ce-4670-b47a-b0596bc0aaa6\",\"resource\":{\"resourceType\":\"Composition\",\"id\":\"ba4fc629-93ce-4670-b47a-b0596bc0aaa6\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_ERP_Composition|1.0.1\"]},\"extension\":[{\"url\":\"https://fhir.kbv.de/StructureDefinition/KBV_EX_FOR_Legal_basis\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_STATUSKENNZEICHEN\",\"code\":\"04\"}}],\"status\":\"final\",\"type\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_FORMULAR_ART\",\"code\":\"e16A\"}]},\"subject\":{\"reference\":\"Patient/\"},\"date\":\"2021-06-16T13:05:38-04:00\",\"author\":[{\"reference\":\"Practitioner/30000000\",\"type\":\"Practitioner\"},{\"type\":\"Device\",\"identifier\":{\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_FOR_Pruefnummer\",\"value\":\"123456\"}}],\"title\":\"elektronische Arzneimittelverordnung\",\"attester\":[{\"mode\":\"legal\",\"party\":{\"reference\":\"Practitioner/30000000\"}}],\"custodian\":{\"reference\":\"Organization/30000000\"},\"section\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Prescription\"}]},\"entry\":[{\"reference\":\"MedicationRequest/028df042-2321-410c-9fa0-148af5d2b909\"}]},{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_ERP_Section_Type\",\"code\":\"Coverage\"}]},\"entry\":[{\"reference\":\"Coverage/\"}]}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Patient/null\",\"resource\":{\"resourceType\":\"Patient\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/identifier-type-de-basis\",\"code\":\"GKV\"}]},\"system\":\"http://fhir.de/NamingSystem/gkv/kvid-10\"}],\"name\":[{\"use\":\"official\",\"family\":\"Heckner\",\"given\":[\"Markus\"],\"prefix\":[\"Dr.\"]}],\"address\":[{\"type\":\"both\",\"line\":[\"Berliner Str. 12\"],\"city\":\"Teltow\",\"postalCode\":\"14513\",\"country\":\"D\",\"_line\":[{\"extension\":null}]}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Practitioner/30000000\",\"resource\":{\"resourceType\":\"Practitioner\",\"id\":\"30000000\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Practitioner|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"LANR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_ANR\",\"value\":\"30000000\"}],\"name\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier\",\"valueString\":\"AC\"}],\"use\":\"official\",\"family\":\"Doctor Last Name\",\"given\":[\"Doctor First Name\"]}],\"qualification\":[{\"code\":{\"coding\":[{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_FOR_Qualification_Type\",\"code\":\"00\",\"display\":\"Arzt-Hausarzt\"}]}},{\"code\":{\"text\":\"Arzt-Hausarzt\"}}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Organization/30000000\",\"resource\":{\"resourceType\":\"Organization\",\"id\":\"30000000\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Organization|1.0.3\",\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Coverage|1.0.3\"]},\"identifier\":[{\"type\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0203\",\"code\":\"BSNR\"}]},\"system\":\"https://fhir.kbv.de/NamingSystem/KBV_NS_Base_BSNR\",\"value\":\"30000000\"}],\"name\":\"null Doctor First Name Doctor Last Name\",\"telecom\":[{\"system\":\"phone\",\"value\":\"030/123456789\"}],\"address\":[{\"type\":\"both\",\"line\":[\"Doctor Street Name Doctor Street Number\"],\"city\":\"Doctor City\",\"postalCode\":\"012345\",\"country\":\"D\"}]}},{\"fullUrl\":\"http://pvs.praxis.local/fhir/Coverage/null\",\"resource\":{\"resourceType\":\"Coverage\",\"meta\":{\"profile\":[\"https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Coverage|1.0.3\"]},\"extension\":[{\"url\":\"http://fhir.de/StructureDefinition/gkv/besondere-personengruppe\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_PERSONENGRUPPE\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/dmp-kennzeichen\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_DMP\",\"code\":\"00\"}},{\"url\":\"http://fhir.de/StructureDefinition/gkv/wop\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_ITA_WOP\",\"code\":\"72\"}},{\"url\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_KBV_VERSICHERTENSTATUS\",\"valueCoding\":{\"system\":\"https://fhir.kbv.de/CodeSystem/KBV_CS_SFHIR_ITA_WOP\",\"code\":\"3\"}}],\"status\":\"active\",\"type\":{\"coding\":[{\"system\":\"http://fhir.de/CodeSystem/versicherungsart-de-basis\",\"code\":\"GKV\"}]},\"beneficiary\":{\"reference\":\"Patient/\"},\"payor\":[{\"identifier\":{\"system\":\"http://fhir.de/NamingSystem/arge-ik/iknr\"},\"display\":\"DENS GmbH\"}]}}]}";

        ValidationResult validationResult =
                prescriptionBundleValidator.validateResource(arbitrarySampleJson,
                        true);
        logger.info(validationResult.getMessages().stream().map(m -> m.getMessage()).collect(Collectors.joining("\n")));

        // Solutions for configuring HAPI validator can be found in a gematik presentation
        // https://gematik.atlassian.net/plugins/servlet/servicedesk/customer/confluence/shim/download/attachments/620855297/20210517%20-%20Sprechstunde%20eRP.pptx?version=1&modificationDate=1621431687594&cacheVersion=1&api=v2
        /*
        https://hapifhir.io/hapi-fhir/docs/tools/hapi_fhir_cli.html

        internalValidator = new FhirInstanceValidator(fhirContext);
        ValidationSupportChain support = new ValidationSupportChain(
                new DefaultProfileValidationSupport(fhirContext),
                new InMemoryTerminologyServerValidationSupport(fhirContext),
                new SnapshotGeneratingValidationSupport(fhirContext),
                new FhirSupport()
        );
        internalValidator.setValidationSupport(support);
        internalValidator.setNoTerminologyChecks(false);
        internalValidator.setAssumeValidRestReferences(false);
        internalValidator.setBestPracticeWarningLevel(IResourceValidator.BestPracticeWarningLevel.Hint);
        validator.registerValidatorModule(internalValidator);
        */
        // TODO: Next issue WARNING - Patient.identifier[0].type - None of the codes provided are in the value set http://hl7.org/fhir/ValueSet/identifier-type (http://hl7.org/fhir/ValueSet/identifier-type), and a code should come from this value set unless it has no suitable code and the validator cannot judge what is suitable) (codes = http://fhir.de/CodeSystem/identifier-type-de-basis#GKV)
        // TODO: Next issue ERROR - Patient.meta.profile[0] - Profile reference 'https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient|1.0.3' has not been checked because it is unknown
        // TODO: None of the codes provided are in the value set http://hl7.org/fhir/ValueSet/identifier-type (http://hl7.org/fhir/ValueSet/identifier-type), and a code should come from this value set unless it has no suitable code and the validator cannot judge what is suitable) (codes = http://fhir.de/CodeSystem/identifier-type-de-basis#GKV)
        // TODO: Profile reference 'https://fhir.kbv.de/StructureDefinition/KBV_PR_FOR_Patient|1.0.3' has not been checked because it is unknown


         assertTrue(validationResult.isSuccessful());
    }

    @Disabled
    @Test
    public void test_Validation_Failure_Of_FHIR_Patient_Resource_With_Missing_Content() {
        Patient patient = new Patient();

        ValidationResult validationResult =
                prescriptionBundleValidator.validateResource(patient, true);
        assertFalse(validationResult.isSuccessful());
    }

    @Disabled
    @Test
    public void test_Successful_Validation_Of_An_FHIR_Coverage_Resource() {
        Coverage coverageResource = prescriptionBundlesBuilder.createCoverageResource();

        ValidationResult validationResult =
                prescriptionBundleValidator.validateResource(coverageResource, true);
        assertTrue(validationResult.isSuccessful());
    }

    @Disabled
    @Test
    public void test_Successful_Validation_Of_XML_Serialization_Of_FHIR_EPrescription_Bundle_Object()
            throws ParseException {
        List<Bundle> prescriptionBundles = prescriptionBundlesBuilder.createBundles();

        prescriptionBundles.forEach(bundle -> {
            ValidationResult validationResult =
                    prescriptionBundleValidator.validateResource(bundle, true);
            assertTrue(validationResult.isSuccessful());
        });
    }
}
