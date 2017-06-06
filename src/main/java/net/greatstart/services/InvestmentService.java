package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.dto.DtoInvestment;
import net.greatstart.mappers.InvestmentMapper;
import net.greatstart.model.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Business logic layer for {@link net.greatstart.model.Investment}.
 */

@Service
@Transactional
public class InvestmentService {

    private InvestmentDao investmentDao;
    private InvestmentMapper investmentMapper;
    private ProjectService projectService;
    private UserService userService;

    @Autowired
    public InvestmentService(InvestmentDao investmentDao,
                             InvestmentMapper investmentMapper,
                             ProjectService projectService,
                             UserService userService) {
        this.investmentDao = investmentDao;
        this.investmentMapper = investmentMapper;
        this.projectService = projectService;
        this.userService = userService;
    }

    public DtoInvestment saveInvestment(DtoInvestment dtoInvestment) {
        Investment investment = getInvestmentFromDtoWithAttachedProjectAndUser(dtoInvestment);
        investment.setDateOfInvestment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        investment.setVerified(false);
        investment.setPaid(false);
        return investmentMapper.fromInvestmentToDto(investmentDao.save(investment));
    }

    public void deleteInvestment(long id) {
        investmentDao.delete(id);
    }

    public DtoInvestment getDtoInvestmentById(long id) {
        return investmentMapper.fromInvestmentToDto(investmentDao.findOne(id));
    }

    public List<DtoInvestment> getAllDtoInvestments() {
        return getDtoInvestments((List<Investment>) investmentDao.findAll());
    }

    private Investment getInvestmentFromDtoWithAttachedProjectAndUser(DtoInvestment dtoInvestment) {
        Investment investment = investmentMapper.investmentFromDto(dtoInvestment);
        investment.setProject(projectService
                .getProjectById(dtoInvestment.getProject().getId()));
        investment.setInvestor(userService
                .getUser(dtoInvestment.getInvestor().getId()));
        return investment;
    }

    public List<DtoInvestment> getDtoProjectInvestments(long id) {
        return getDtoInvestments(projectService.getProjectById(id).getInvestments());
    }

    public List<DtoInvestment> getUserDtoInvestmentsByUserEmail(String userEmail) {
        return getDtoInvestments(userService.getUserByEmail(userEmail).getInvestments());
    }

    private List<DtoInvestment> getDtoInvestments(List<Investment> investments) {
        List<DtoInvestment> dtoInvestments = new ArrayList<>();
        investments.forEach(investment -> dtoInvestments
                .add(investmentMapper.fromInvestmentToDto(investment))
        );
        return dtoInvestments;
    }
}
