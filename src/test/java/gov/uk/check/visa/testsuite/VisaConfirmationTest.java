package gov.uk.check.visa.testsuite;

import gov.uk.check.visa.pages.*;
import gov.uk.check.visa.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisaConfirmationTest extends BaseTest {
    DurationOfStayPage durationOfStayPage;
    FamilyImmigrationStatusPage familyImmigrationStatusPage;
    ReasonForTravelPage reasonForTravelPage;
    ResultPage resultPage;
    SelectNationalityPage selectNationalityPage;
    StartPage startPage;
    WorkTypePage workTypePage;

    @BeforeMethod(groups = {"sanity", "smoke", "regression"})
    public void inIt() {
        durationOfStayPage = new DurationOfStayPage();
        familyImmigrationStatusPage = new FamilyImmigrationStatusPage();
        reasonForTravelPage = new ReasonForTravelPage();
        resultPage = new ResultPage();
        selectNationalityPage = new SelectNationalityPage();
        startPage = new StartPage();
        workTypePage = new WorkTypePage();
    }

    @Test(groups = {"smoke", "sanity", "regression"})
    public void anAustralianCominToUKForTourism() {


        //        Click on start button
        startPage.startNowButton();

//        Select a Nationality 'Australia'
        selectNationalityPage.selectNationality("Australia");
//        Click on Continue button
        selectNationalityPage.clickNextStepButton();
//        Select reason 'Tourism'
        reasonForTravelPage.clickOnReasonForVisitTourism();
//        Click on Continue button
        reasonForTravelPage.clickNextStepButton();
//        verify result 'You will not need a visa to come to the UK'
        String actualResultMessage = resultPage.getResultMessage();
        String expectedResultMessage = "You will not need a visa to come to the UK";
        Assert.assertEquals(actualResultMessage, expectedResultMessage);
    }

    @Test(groups = {"regression"})
    public void aChileanComingToTheUKForWorkAndPlansOnStayingForLongerThanSixMonths() {

//        Click on start button
        startPage.startNowButton();
//        Select a Nationality 'Chile'
        selectNationalityPage.selectNationality("Chile");
//        Click on Continue button
        selectNationalityPage.clickNextStepButton();

//        Select reason 'Work, academic visit or business'
        reasonForTravelPage.clickOnReasonForVisitAcademic();

//        Click on Continue button
        reasonForTravelPage.clickNextStepButton();
//        Select intendent to stay for 'longer than 6 months'
        durationOfStayPage.selectLengthOfStay("more");
//        Click on Continue button
        durationOfStayPage.clickNextStepButton();
//        Select have planning to work for 'Health and care professional'
        workTypePage.clickOnHealthAndCareProvision();

//        Click on Continue button
        workTypePage.clickNextStepButton();
//        verify result 'You need a visa to work in health and care'
        String actualSuccessMessage = resultPage.getResultMessageforHealthAndCare();
        Assert.assertTrue(actualSuccessMessage.contains("You need a visa to work in health and care"));
    }

    @Test(groups = {"sanity", "regression"})
    public void aColumbianNationalComingToTheUKToJoinAPartnerForALongStayTheyDoHaveAnArticle10Or20Card() {

//        Click on start button
        startPage.startNowButton();
//        Select a Nationality 'Colombia'
        selectNationalityPage.selectNationality("Colombia");
//        Click on Continue button
        selectNationalityPage.clickNextStepButton();
//        Select reason 'Join partner or family for a long stay'
        reasonForTravelPage.clickOnReasonForJoinPartnerAndFamily();
//        Click on Continue button
        reasonForTravelPage.clickNextStepButton();
//        Select state My partner of family member have uk immigration status 'yes'
        familyImmigrationStatusPage.selectImmigrationStatus("yes");
//        Click on Continue button
        familyImmigrationStatusPage.clickNextStepButton();
//        verify result 'You’ll need a visa to join your family or partner in the UK'

    }
}
