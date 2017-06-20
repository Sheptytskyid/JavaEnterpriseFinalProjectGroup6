package net.greatstart.controllers;

import net.greatstart.dto.DtoInterest;
import net.greatstart.model.InvestmentInterest;
import net.greatstart.model.User;
import net.greatstart.services.InvestmentInterestService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * A controller to work with {@link net.greatstart.model.InvestmentInterest}.
 */

@RequestMapping("/api/interest")
@RestController
public class InvestmentInterestController {
    private static final String REDIRECT_TO_INVESTMENT_INTEREST = "redirect:/invinterest/";

    private InvestmentInterestService investmentInterestService;
    private UserService userService;

    @Autowired
    public InvestmentInterestController(InvestmentInterestService investmentInterestService, UserService userService) {
        this.investmentInterestService = investmentInterestService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<DtoInterest>> getAllInterests() {
        List<DtoInterest> interests = investmentInterestService.getAllDtoInterest();
        return new ResponseEntity<>(interests, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DtoInterest> getInterestById(@PathVariable long id) {
        DtoInterest dtoInterest = investmentInterestService.getDtoInterestById(id);
        if (dtoInterest != null) {
            return new ResponseEntity<>(dtoInterest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{id}")
    public ResponseEntity<DtoInterest> updateProject(
            @PathVariable("id") long id,
            @Valid @RequestBody DtoInterest dtoInterest) {
        if (userService.getLoggedInUser().getId() != dtoInterest.getInvestor().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        DtoInterest currentInterest = investmentInterestService.updateDtoInterest(dtoInterest);
        if (currentInterest != null) {
            return new ResponseEntity<>(dtoInterest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<DtoInterest> createInterest(@Valid @RequestBody DtoInterest dtoInterest) {
        dtoInterest = investmentInterestService.createNewInterestFromDto(dtoInterest);
        if (dtoInterest != null) {
            return new ResponseEntity<>(dtoInterest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("my")
    public ResponseEntity<Collection<DtoInterest>> getMyInterests(Principal principal) {
        List<DtoInterest> dtoInterests = investmentInterestService.getUserDtoInterestsByUserEmail(principal.getName());
        return new ResponseEntity<>(dtoInterests, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{id}")
    public ResponseEntity<DtoInterest> deleteInterestById(@PathVariable long id) {
        if (investmentInterestService.getDtoInterestById(id) != null) {
            investmentInterestService.deleteInvestmentInterest(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}