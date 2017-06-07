package net.greatstart.mappers;

import net.greatstart.dto.DtoInvestment;
import net.greatstart.model.Investment;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static net.greatstart.MapperHelper.*;
import static org.junit.Assert.assertEquals;

/**
 * {@link }
 *
 * @author Oleksii Polite Rudenko
 * @version 1.0
 */
public class InvestmentMapperTest {

    InvestmentMapper investmentMapper = Mappers.getMapper(InvestmentMapper.class);

    @Test
    public void fromInvestmentToDto() throws Exception {
        //init
        Investment investment = getTestInvestment(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        DtoInvestment expected = getTestDtoInvestment(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        expected.getProject().getOwner().setPhoto(null);
        expected.getProject().getOwner().setPhoneNumber(null);
        expected.getProject().getOwner().setAddress(null);
        expected.getProject().getOwner().setName(null);
        expected.getProject().getOwner().setLastName(null);
        expected.getProject().getOwner().setId(0L);
        expected.getProject().getOwner().setEmail(null);
        expected.getProject().getDesc().setOther("");
        expected.getInvestor().setAddress(null);
        expected.getInvestor().setPhoneNumber(null);
        expected.getInvestor().setPhoto(null);
        //use
        DtoInvestment result = investmentMapper.fromInvestmentToDto(investment);
        //check
        assertEquals(expected, result);
    }

    @Test
    public void investmentFromDto() throws Exception {
    }

}