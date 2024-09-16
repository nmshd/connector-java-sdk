package eu.enmeshed.model.attributes.values;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.enmeshed.model.attributes.values.identity.Affiliation;
import eu.enmeshed.model.attributes.values.identity.AffiliationOrganization;
import eu.enmeshed.model.attributes.values.identity.AffiliationRole;
import eu.enmeshed.model.attributes.values.identity.AffiliationUnit;
import eu.enmeshed.model.attributes.values.identity.BirthCity;
import eu.enmeshed.model.attributes.values.identity.BirthCountry;
import eu.enmeshed.model.attributes.values.identity.BirthDate;
import eu.enmeshed.model.attributes.values.identity.BirthDay;
import eu.enmeshed.model.attributes.values.identity.BirthMonth;
import eu.enmeshed.model.attributes.values.identity.BirthName;
import eu.enmeshed.model.attributes.values.identity.BirthPlace;
import eu.enmeshed.model.attributes.values.identity.BirthState;
import eu.enmeshed.model.attributes.values.identity.BirthYear;
import eu.enmeshed.model.attributes.values.identity.Citizenship;
import eu.enmeshed.model.attributes.values.identity.City;
import eu.enmeshed.model.attributes.values.identity.CommunicationLanguage;
import eu.enmeshed.model.attributes.values.identity.Country;
import eu.enmeshed.model.attributes.values.identity.DeliveryBoxAddress;
import eu.enmeshed.model.attributes.values.identity.DisplayName;
import eu.enmeshed.model.attributes.values.identity.EMailAddress;
import eu.enmeshed.model.attributes.values.identity.FaxNumber;
import eu.enmeshed.model.attributes.values.identity.GivenName;
import eu.enmeshed.model.attributes.values.identity.HonoricPrefix;
import eu.enmeshed.model.attributes.values.identity.HonoricSuffix;
import eu.enmeshed.model.attributes.values.identity.HouseNumber;
import eu.enmeshed.model.attributes.values.identity.IdentityFileReference;
import eu.enmeshed.model.attributes.values.identity.JobTitle;
import eu.enmeshed.model.attributes.values.identity.MiddleName;
import eu.enmeshed.model.attributes.values.identity.Nationality;
import eu.enmeshed.model.attributes.values.identity.PersonName;
import eu.enmeshed.model.attributes.values.identity.PhoneNumber;
import eu.enmeshed.model.attributes.values.identity.PostOfficeBoxAddress;
import eu.enmeshed.model.attributes.values.identity.Pseudonym;
import eu.enmeshed.model.attributes.values.identity.SchematizedXML;
import eu.enmeshed.model.attributes.values.identity.Sex;
import eu.enmeshed.model.attributes.values.identity.State;
import eu.enmeshed.model.attributes.values.identity.Street;
import eu.enmeshed.model.attributes.values.identity.StreetAddress;
import eu.enmeshed.model.attributes.values.identity.Surname;
import eu.enmeshed.model.attributes.values.identity.Website;
import eu.enmeshed.model.attributes.values.identity.ZipCode;
import eu.enmeshed.model.attributes.values.proprietary.ProprietaryBoolean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
    @JsonSubTypes.Type(Affiliation.class),
    @JsonSubTypes.Type(AffiliationOrganization.class),
    @JsonSubTypes.Type(AffiliationRole.class),
    @JsonSubTypes.Type(AffiliationUnit.class),
    @JsonSubTypes.Type(BirthCity.class),
    @JsonSubTypes.Type(BirthCountry.class),
    @JsonSubTypes.Type(BirthDate.class),
    @JsonSubTypes.Type(BirthDay.class),
    @JsonSubTypes.Type(BirthMonth.class),
    @JsonSubTypes.Type(BirthName.class),
    @JsonSubTypes.Type(BirthPlace.class),
    @JsonSubTypes.Type(BirthState.class),
    @JsonSubTypes.Type(BirthYear.class),
    @JsonSubTypes.Type(Citizenship.class),
    @JsonSubTypes.Type(City.class),
    @JsonSubTypes.Type(CommunicationLanguage.class),
    @JsonSubTypes.Type(Country.class),
    @JsonSubTypes.Type(DeliveryBoxAddress.class),
    @JsonSubTypes.Type(DisplayName.class),
    @JsonSubTypes.Type(EMailAddress.class),
    @JsonSubTypes.Type(FaxNumber.class),
    @JsonSubTypes.Type(GivenName.class),
    @JsonSubTypes.Type(HonoricPrefix.class),
    @JsonSubTypes.Type(HonoricSuffix.class),
    @JsonSubTypes.Type(HouseNumber.class),
    @JsonSubTypes.Type(IdentityFileReference.class),
    @JsonSubTypes.Type(JobTitle.class),
    @JsonSubTypes.Type(MiddleName.class),
    @JsonSubTypes.Type(Nationality.class),
    @JsonSubTypes.Type(PersonName.class),
    @JsonSubTypes.Type(PhoneNumber.class),
    @JsonSubTypes.Type(PostOfficeBoxAddress.class),
    @JsonSubTypes.Type(ProprietaryBoolean.class),
    @JsonSubTypes.Type(Pseudonym.class),
    @JsonSubTypes.Type(SchematizedXML.class),
    @JsonSubTypes.Type(Sex.class),
    @JsonSubTypes.Type(State.class),
    @JsonSubTypes.Type(Street.class),
    @JsonSubTypes.Type(StreetAddress.class),
    @JsonSubTypes.Type(Surname.class),
    @JsonSubTypes.Type(Website.class),
    @JsonSubTypes.Type(ZipCode.class),
})
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class AttributeValue {

}
