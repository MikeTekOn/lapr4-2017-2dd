/**
 * Technical documentation regarding the user story Core10.1.2: Company Contact.
 * <p>
 * <b>JIRA UserStory id: </b>
 * <p>
 *
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
 * 
 *  
 * <b>Analysis</b><p>
 * <img src="us10.1.2_concept_classes.png" alt="image">
 * <p>
 * <i>Notes:</i><p>
 * The profession should be selected from a list. The list of professions should be loaded (and/or updated) from a
 * external XML file or an existing configuration file of Cleansheets.
 * <p>
 * <i>Company Contact Window:</i>
 * <ul>
 *     <li>Contact Info</li>
 *     <li>Person contacts related to</li>
 *     <li>Agenda (Company events are the aggregate of all it's related person contacts events</li>
 * </ul>
 *
 *
 * <i>Concept Classes:</i> CompanyContact, PersonContact, Agenda, Event, Profession<p>
 *     <i>Refactoring:</i>
 * <ul>
 *     <li>Contact interface</li>
 *     <li>Contact to PersonalContact</li>
 *     <li>Add Profession and Company to PersonalContact (optional atributes?)</li>
 *
 * </ul>
 *
 *
 * <b>Tests</b><p>
 * <ul>
 *     <li>CompanyContactService::ensureAllRelatedContactsAreFound</li>
 *     <li>CompanyContactService::ensureAllRelatedContactsEventsAreInCompanyAgenda</li>
 *     <li>CompanyContactService::ensureCompanyAgendaIsReadOnly</li>
 *
 *     <li>CompanyName::ensureNameIsNonNull</li>
 *     <li>CompanyName::ensureNameIsNotEmpty</li>
 *     <li>Profession::ensureProfessionIsNonNull</li>
 *     <li>Profession::ensureProfessionIsNotEmpty</li>
 *     <li>Image::ensureImageIsNonNull</li>
 *     <li>Image::ensureDefaultImageCanBeConstructed</li>
 *     <li>CompanyName::ensureNameIsNonNull</li>
 *     <li>CompanyContact::ensureEmailIsNonNull</li>
 *     <li>CompanyContact::ensurePhoneNumberIsNonNull</li>
 *     <li>CompanyContact::ensureImageCanBeNull</li>
 * </ul>
 *
 * <b>Design</b><p>
 * <img src="us10.1.2_comp_contacts_window.png" alt="image">
 *
 * <p>
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
package lapr4.green.s2.core.n1150738.contacts;

