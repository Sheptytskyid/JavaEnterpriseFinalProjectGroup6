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
        List<DtoInvestment> investments = new ArrayList<>();
        investmentDao.findAll().forEach(investment -> investments
                .add(investmentMapper.fromInvestmentToDto(investment))
        );
        return investments;
    }

    private Investment getInvestmentFromDtoWithAttachedProjectAndUser(DtoInvestment dtoInvestment) {
        Investment investment = investmentMapper.investmentFromDto(dtoInvestment);
        investment.setProject(projectService
                .getProjectById(dtoInvestment.getProject().getId()));
        investment.setInvestor(userService
                .getUser(dtoInvestment.getInvestor().getId()));
        return investment;
    }
}
