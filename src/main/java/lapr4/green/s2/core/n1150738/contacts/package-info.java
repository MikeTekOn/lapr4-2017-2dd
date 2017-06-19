/**
 * <h2>Technical documentation regarding the user story Core10.1.2: Company Contact.</h2>
 * <p>
 * <b>JIRA UserStory id: LAPR4E17DD-31</b>
 * <p>
 * Author: Henrique Oliveira [1150738@isep.ipp.pt]
 * <p>
 * <p>
 * <b>Requirement</b><p>
 * Statement:<p>
 * A contact may also be a company.. If a contact is a company then it has a name (no first and no last name),
 * A person contact may now be related to a company contact. A person contact may have also a profession. The
 * profession should be selected from a list. The list of professions should be loaded (and/or updated) from a
 * external XML file or an existing configuration file of Cleansheets. The window for company contacts should display
 * all the person contacts that are related to it. The company window should also have an agenda. The agenda of a
 * company should be read only and displays all the events of the individual contacts that are related to it.
 * <p>
 * <p>
 * <p>
 * <p>
 * <b>Analysis</b><p>
 * <img src="us10.1.2_concept_classes.png" alt="image">
 * <p>
 * <i>Notes:</i><p>
 * The profession should be selected from a list. The list of professions should be loaded (and/or updated) from a
 * external XML file or an existing configuration file of Cleansheets.
 * <p>
 * <i>What Already Exists:</i> This project already has a use case to add Contacts with first and last name, email
 * and photo. Contact also has an agenda (collection of events). Contacts are persisted with the app.
 * <p>
 * <i>What has to be added:</i> Profession and Company to the existing {@link lapr4.white.s1.core.n4567890.contacts.domain.Contact} concept and functionalities to create
 * a Company Contact and display its info.
 * <p>
 * <i><u>Existing Classes:</u></i> {@link lapr4.white.s1.core.n4567890.contacts.domain.Contact}, {@link lapr4.white.s1.core.n4567890.contacts.domain.Agenda}, {@link lapr4.white.s1.core.n4567890.contacts.domain.Event}<p>
 * <i><u>New Concept Classes:</u></i> CompanyContact, Profession, CompanyName, EmailAddress, PhoneNumber<p>
 * <i><u>Refactoring:</u></i>
 * <ul>
 * <li>Add Profession and Company to {@link lapr4.white.s1.core.n4567890.contacts.domain.Contact} (optional atributes)</li>
 * </ul>
 * <p>
 * <i><u>What needs to be displayed in Company Contact Window:</u></i>
 * <ul>
 * <li>Contact Info</li>
 * <li>Person contacts related to</li>
 * <li>Agenda (Company events are the aggregate of all it's related person contacts events</li>
 * </ul>
 * <p>
 * <p>
 * <p>
 * <p>
 * <b>Tests</b>
 * <p>
 * <ul>
 * <li>CompanyContactService::ensureAllRelatedContactsAreFound</li>
 * <li>CompanyContactService::ensureAllRelatedContactsEventsAreInCompanyAgenda</li>
 * <li>CompanyContactService::ensureCompanyAgendaIsReadOnly</li>
 * <li>CompanyName::ensureNameIsNonNull</li>
 * <li>CompanyName::ensureNameIsNotEmpty</li>
 * <li>Profession::ensureProfessionIsNonNull</li>
 * <li>Profession::ensureProfessionIsNotEmpty</li>
 * <li>PhoneNumber::ensureProfessionIsNonNull</li>
 * <li>PhoneNumber::ensureProfessionIsNotEmpty</li>
 * <li>EmailAddress::ensureProfessionIsNonNull</li>
 * <li>EmailAddress::ensureProfessionIsNotEmpty</li>
 * <li>Image::ensureImageIsNonNull</li>
 * <li>Image::ensureDefaultImageCanBeConstructed</li>
 * <li>CompanyName::ensureNameIsNonNull</li>
 * <li>CompanyContact::ensureEmailIsNonNull</li>
 * <li>CompanyContact::ensurePhoneNumberIsNonNull</li>
 * <li>CompanyContact::ensureImageCanBeNull</li>
 * </ul>
 * <p>
 * <p>
 * <p>
 * <p>
 * <b>Design</b><p>
 * <b>Create Contact Use Case:</b>
 * <p>
 * This (previously developed) Use Case now has to contemplate two new (optional) attributes of contact - Profession and CompanyContact.
 * In order to do that it has to be added a selector for externally loaded professions (XML file in resources) and also
 * a selector for existing Company Contacts.
 * <p>
 * <img src="us10.1.2_create_contact.png" alt="image" width="100%">
 * <p>
 * <b>Creating Company Contact</b>
 * <p>
 * <img src="us10.1.2_create_company_contact.png" alt="image" width="100%">
 * <p>
 * <b>Showing Company Contact Info - Company Contact Window</b>
 * <p>
 * <img src="us10.1.2_comp_contacts_window.png" alt="image">
 * <p>
 * <b>Importing Professions</b>
 * <p>
 * To import professions there should be a ProfessionImporterService that parses the external XML file and returns a
 * list of Professions.
 * <p>
 * The XML format chosen is the following:
 * <pre>
 * {@code
 *   <professions>
 *       <profession>
 *           <name>Accountant</name>
 *       </profession>
 *       <profession>
 *           <name>Auditor</name>
 *       </profession>
 *       <profession>
 *           <name>Engineer</name>
 *       </profession>
 *   </professions>
 * }
 * </pre>
 * <p>
 * <p>
 * <p>
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
package lapr4.green.s2.core.n1150738.contacts;

