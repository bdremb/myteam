package by.example.myteam.controller;


import by.example.myteam.entity.ExtraInfo;
import by.example.myteam.entity.Person;
import by.example.myteam.service.ExtraInfoService;
import by.example.myteam.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/team")
public class PersonController {
    final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    //private static final Marker FILE = MarkerFactory.getMarker("FILE");
    private final PersonService personServiсe;
    private final ExtraInfoService extraInfoService;

    @Autowired
    public PersonController(PersonService personServise, ExtraInfoService extraInfoService) {
        this.personServiсe = personServise;
        this.extraInfoService = extraInfoService;
    }

    @GetMapping("/persons")
    public String showAllPersons(Model model) {
        List<Person> pers = personServiсe.getAllPerson();
        model.addAttribute("allPersons", pers);
        return "list-persons";
    }

    @GetMapping("/persons/{id}")
    public String showPersonDetailsById(@PathVariable("id") int id, Model model) {
        Person person = personServiсe.getPerson(id);
        model.addAttribute("person", person);
        return "person-details";
    }


    @PostMapping("/persons")
    public String saveNewPerson(@ModelAttribute("person") @Valid Person pers,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.error("binding result has errors");
            return "register";
        }
        if (pers.getPassword().equals(pers.getConfirmPassword())) {
            if (personServiсe.savePerson(pers)) {
                return "login";
            }
        }
        model.addAttribute("info", "Измените логин или проверьте правильность подтверждения пароля");
        model.addAttribute("page", "/register");
        return "error-page";
    }

    @GetMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable("id") int id, Model model) {
        personServiсe.deletePerson(id);
        List<Person> pers = personServiсe.getAllPerson(); // проверить на 2 запроса на f12
        model.addAttribute("allPersons", pers);
        return "list-persons";                       //redirect
    }

    @GetMapping("/persons/update/{id}")
    public String updatePerson(@PathVariable("id") int id, Model model) {
        Person person = personServiсe.getPerson(id);
        model.addAttribute("person", person);
        model.addAttribute("extrainfo", person.getExtraInfo());  // зачем
        return "person-page";
    }


    @PostMapping("/login")
    public String enter(@ModelAttribute("person") Person person, Model model) {
        Person newPerson = personServiсe.validateAndGetPerson(person);
        if (newPerson != null) {                                //not null   ,Objects not null
            model.addAttribute("person", newPerson);
            model.addAttribute("extrainfo", newPerson.getExtraInfo()); // так не должно быть
            logger.info("enter to the person page");
            return "person-page";
        }
        return "redirect:/login";
    }

    @PostMapping("/addinfo")
    public String saveExtraInfoOfPerson(@ModelAttribute("extrainfo") ExtraInfo extraInfo,   //переделать, убрать
                                        @ModelAttribute("person") Person person, Model model) {
        ExtraInfo info = personServiсe.saveExtraInfoOfPerson(extraInfo, person);
        extraInfoService.saveExtraInfo(info);
        model.addAttribute("person", info.getPerson());
        return "person-page";
    }
}
