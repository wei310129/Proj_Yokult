package web.fundraising.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import web.fundraising.dao.PlanDAO;

@Entity
@Table(name="Fund_PLAN")
public class PlanBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planID;
	private String planName;
	private Integer planAmount;
	private String planContent;
	private String planPostNote;
	private String planPostDate;
	private Timestamp planStartedDateTime;
	private Timestamp planEndedDateTime;
	private byte[] planPicture;
	private Integer	proposalID;
//	private String planAttendeeCount;
	@Transient
	private Integer planPurchases;	
	@Transient
	private String planPictureBase64;
	@Transient
	private long planStartedDateTimeMillis;
	@Transient
	private long planEndedDateTimeMillis;
	

	public void renewBean(PlanDAO planDAO) {
		List<OrderBean> list_orderBean = planDAO.getRelationalOrders(this);
		this.setPlanPurchases(list_orderBean.size());
		this.planPictureBase64 = Base64.getEncoder().encodeToString(this.planPicture);
		this.planStartedDateTimeMillis = this.planStartedDateTime.getTime();
		this.planEndedDateTimeMillis = this.planEndedDateTime.getTime();
	}
	
	public PlanBean() {}


	public PlanBean(Integer planID, String planName, Integer planAmount, String planContent, String planPostNote,
			String planPostDate, Timestamp planStartedDateTime, Timestamp planEndedDateTime, byte[] planPicture,
			Integer proposalID, Integer planPurchases, String planPictureBase64) {
		super();
		this.planID = planID;
		this.planName = planName;
		this.planAmount = planAmount;
		this.planContent = planContent;
		this.planPostNote = planPostNote;
		this.planPostDate = planPostDate;
		this.planStartedDateTime = planStartedDateTime;
		this.planEndedDateTime = planEndedDateTime;
		this.planPicture = planPicture;
		this.proposalID = proposalID;
		this.planPurchases = planPurchases;
		this.planPictureBase64 = planPictureBase64;
	}
	
	public PlanBean(String planName, Integer planAmount, String planContent, String planPostNote,
			String planPostDate, Timestamp planStartedDateTime, Timestamp planEndedDateTime, byte[] planPicture,
			Integer proposalID, Integer planPurchases, String planPictureBase64) {
		super();
		this.planName = planName;
		this.planAmount = planAmount;
		this.planContent = planContent;
		this.planPostNote = planPostNote;
		this.planPostDate = planPostDate;
		this.planStartedDateTime = planStartedDateTime;
		this.planEndedDateTime = planEndedDateTime;
		this.planPicture = planPicture;
		this.proposalID = proposalID;
		this.planPurchases = planPurchases;
		this.planPictureBase64 = planPictureBase64;
	}

	@Override
	public String toString() {
		return "PlanBean [planID=" + planID + ", planName=" + planName + ", planAmount=" + planAmount + ", planContent="
				+ planContent + ", planPostNote=" + planPostNote + ", planPostDate=" + planPostDate
				+ ", planStartedDateTime=" + planStartedDateTime + ", planEndedDateTime=" + planEndedDateTime
				+ ", planPicture=" + Arrays.toString(planPicture) + ", proposalID=" + proposalID + ", planPurchases="
				+ planPurchases + ", planPictureBase64=" + planPictureBase64 + "]";
	}

	public Integer getPlanID() {
		return planID;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Integer planAmount) {
		this.planAmount = planAmount;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public String getPlanPostNote() {
		return planPostNote;
	}

	public void setPlanPostNote(String planPostNote) {
		this.planPostNote = planPostNote;
	}

	public String getPlanPostDate() {
		return planPostDate;
	}

	public void setPlanPostDate(String planPostDate) {
		this.planPostDate = planPostDate;
	}

	public Timestamp getPlanStartedDateTime() {
		return planStartedDateTime;
	}

	public void setPlanStartedDateTime(Timestamp planStartedDateTime) {
		this.planStartedDateTime = planStartedDateTime;
	}

	public Timestamp getPlanEndedDateTime() {
		return planEndedDateTime;
	}

	public void setPlanEndedDateTime(Timestamp planEndedDateTime) {
		this.planEndedDateTime = planEndedDateTime;
	}

	public byte[] getPlanPicture() {
		return planPicture;
	}

	public void setPlanPicture(byte[] planPicture) {
		this.planPicture = planPicture;
	}

	public Integer getProposalID() {
		return proposalID;
	}

	public void setProposalID(Integer proposalID) {
		this.proposalID = proposalID;
	}

	public Integer getPlanPurchases() {
		return planPurchases;
	}

	public void setPlanPurchases(Integer planPurchases) {
		this.planPurchases = planPurchases;
	}

	public String getPlanPictureBase64() {
		return planPictureBase64;
	}

	public void setPlanPictureBase64(String planPictureBase64) {
		this.planPictureBase64 = planPictureBase64;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
