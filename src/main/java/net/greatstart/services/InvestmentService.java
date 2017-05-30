package net.greatstart.services;

import net.greatstart.dao.InvestmentDao;
import net.greatstart.dto.DtoInvestment;
import net.greatstart.mappers.CycleAvoidingMappingContext;
import net.greatstart.mappers.InvestmentMapper;
import net.greatstart.model.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvestmentService {

    private InvestmentDao investmentDao;
    private InvestmentMapper investmentMapper;

    @Autowired
    public InvestmentService(InvestmentDao investmentDao, InvestmentMapper investmentMapper) {
        this.investmentDao = investmentDao;
        this.investmentMapper = investmentMapper;
    }

    public Investment saveInvestment(Investment investment) {
        return investmentDao.save(investment);
    }

    public void deleteInvestment(long id) {
        investmentDao.delete(id);
    }

    public Investment getInvestmentById(long id) {
        return investmentDao.findOne(id);
    }

    public DtoInvestment getDtoInvestmentById(long id) {
        return investmentMapper.fromInvestmentToDto(investmentDao.findOne(id), new CycleAvoidingMappingContext());
    }

    public List<Investment> getAllInvestments() {
        List<Investment> investments = new ArrayList<>();
        investmentDao.findAll().forEach(investments::add);
        return investments;
    }

    public List<DtoInvestment> getAllDtoInvestments() {
        List<DtoInvestment> investments = new ArrayList<>();
        investmentDao.findAll().forEach(investment -> investments.add(investmentMapper
                .fromInvestmentToDto(investment, new CycleAvoidingMappingContext())));
        return investments;
    }
}
