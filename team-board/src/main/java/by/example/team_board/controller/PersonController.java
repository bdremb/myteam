package by.example.team_board.controller;

import by.example.team_board.entity.ExtraInfo;
import by.example.team_board.entity.Person;
import by.example.team_board.exceptions.PersonAlreadyExistException;
import by.example.team_board.page.Pages;
import by.example.team_board.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static by.example.team_board.page.Pages.ERROR_PAGE;
import static by.example.team_board.page.Pages.LIST_PERSONS;
import static by.example.team_board.page.Pages.PERSON_DETAILS;
import static by.example.team_board.page.Pages.PERSON_PAGE;

/**
 * Person controller.
 *
 * @author Denis
 * @version 1.0
 */
@Controller
@RequestMapping("/team")
public class PersonController {
  final Logger logger = LoggerFactory.getLogger(PersonController.class);

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Method for getting a list of persons.
   *
   * @param model includes a list of all persons
   * @return list person page from Pages enum
   * @see Pages#LIST_PERSONS
   */
  @GetMapping("/persons")
  public String showAllPersons(Model model) {
    List<Person> persons = personService.getAllPersons();
    model.addAttribute("allPersons", persons);
    return LIST_PERSONS.getPage();
  }

  /**
   * Method for getting a single person.
   *
   * @param id    the Person id to show person details
   * @param model the Person
   * @return the name of the person details page
   */
  @GetMapping("/persons/{id}")
  public String showPersonDetailsById(@PathVariable("id") int id, Model model) {
    Person person = personService.getPerson(id);
    model.addAttribute("person", person);
    return PERSON_DETAILS.getPage();  //static import
  }

  /**
   * Method for a save new person.
   *
   * @param person        the Person to save
   * @param bindingResult new {@link Person} data entry errors
   * @param model         the Person
   * @return the name of the error page
   * @see Pages#ERROR_PAGE
   */
  @PostMapping("/persons")
  public String saveNewPerson(@ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      logger.error("Binding result has errors: " + bindingResult.toString());
      return Pages.REGISTER.getPage();
    }
    if (person.getPassword().equals(person.getConfirmPassword())) {
      person.setExtraInfo(new ExtraInfo());
      try {
        return personService.savePerson(person).getPage();
      } catch (PersonAlreadyExistException e) {
        logger.error("Person with login: <{}> already exist", person.getLogin());
        model.addAttribute("info", "Change your username");
        return Pages.ERROR_PAGE.getPage();
      }
    }
    model.addAttribute("info", "Change your password");
    model.addAttribute("page", "/register");
    return ERROR_PAGE.getPage();
  }

  /**
   * Delete a person.
   *
   * @param id    the id of the Person to be deleted
   * @param model list of existing Persons
   * @return list person page from Pages enum
   */
  @GetMapping("/persons/delete/{id}")
  public String deletePerson(@PathVariable("id") int id, Model model) {
    personService.deletePerson(id);
    return showAllPersons(model);
  }

  /**
   * Update person.
   *
   * @param id    the id of the Person to be update
   * @param model Person with updated data
   * @return the name of the person page
   * @see Pages#PERSON_PAGE
   */
  @GetMapping("/persons/update/{id}")
  public String updatePerson(@PathVariable("id") int id, Model model) {
    Person person = personService.getPerson(id);
    model.addAttribute("person", person);
    return PERSON_PAGE.getPage();
  }

  /**
   * Login.
   *
   * @param person Person with data for authentication
   * @param model  the Person
   * @return redirect to login page
   * @see Pages#PERSON_PAGE
   */
  @PostMapping("/login")
  public String login(@ModelAttribute("person") Person person, Model model) {
    Person newPerson = personService.authorize(person);
    if (Objects.nonNull(newPerson)) {
      model.addAttribute("person", newPerson);
      logger.info("Login completed successfully.");
      return PERSON_PAGE.getPage();
    }
    return "redirect:/login";
  }

  /**
   * Method for updating additional data.
   *
   * @param person the Person who needs to update ExtraData
   * @param model  Person with updated ExtraData
   * @return the name of the person page
   * @see Pages#PERSON_PAGE
   */
  @PostMapping("/addinfo")
  public String updateExtraInfoOfPerson(@ModelAttribute("person") Person person, Model model) {
    model.addAttribute("person", personService.updateExtraInfoOfPerson(person));
    return PERSON_PAGE.getPage();
  }
}
