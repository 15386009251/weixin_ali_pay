package com.rumo.pojo;

import java.util.Date;

public class Order {

	private Long id;
	private String orderNumber;
	private String payment;
	private Integer paymentType;
	private Integer isPay;
	private Date created;
	private Date updated;
	private Date payTime;
	private Long userId;
	// 评论相关
	private String buyerMessage;
	private String buyerNick;
	private Integer buyerRate;
	private Integer driverRate;// 司机星级
	private Integer centerRate;// 商家星级
	private String centerMessage;// 商家评论
	private String driverMessage;// 司机评论
	private Long centerId;
	private Long salemanId;
	private Long drivermanId;
	private Integer isDistribute;
	private Integer isSort;
	private Integer isStore;
	private Integer isShip;
	private Date distributeTime;
	private Date sortTime;
	private Date storeTime;
	private Date shipTime;
	private Date commentTime;
	private Date outstoreTime;
	private Long supplierId;
	private Long trainermanId;

	private Integer isDone;// 申请状态0未申请1申请2已结算
	private Date doneTime;// 申请时间
	private Date doneFinishTime;// 结算时间
	private Long financeId;// 财务ID

	public Order() {
		super();
	}
	
	
	/**
	 * 创建一个新的实例 Order.
	 * @param id
	 * @param orderNumber
	 * @param payment
	 * @param paymentType
	 * @param isPay
	 * @param created
	 * @param updated
	 * @param payTime
	 * @param userId
	 * @param buyerMessage
	 * @param buyerNick
	 * @param buyerRate
	 * @param driverRate
	 * @param centerRate
	 * @param centerMessage
	 * @param driverMessage
	 * @param centerId
	 * @param salemanId
	 * @param drivermanId
	 * @param isDistribute
	 * @param isSort
	 * @param isStore
	 * @param isShip
	 * @param distributeTime
	 * @param sortTime
	 * @param storeTime
	 * @param shipTime
	 * @param commentTime
	 * @param outstoreTime
	 * @param supplierId
	 * @param trainermanId
	 * @param isDone
	 * @param doneTime
	 * @param doneFinishTime
	 * @param financeId
	 */
	public Order(Long id, String orderNumber, String payment, Integer paymentType, Integer isPay, Date created,
			Date updated, Date payTime, Long userId, String buyerMessage, String buyerNick, Integer buyerRate,
			Integer driverRate, Integer centerRate, String centerMessage, String driverMessage, Long centerId,
			Long salemanId, Long drivermanId, Integer isDistribute, Integer isSort, Integer isStore, Integer isShip,
			Date distributeTime, Date sortTime, Date storeTime, Date shipTime, Date commentTime, Date outstoreTime,
			Long supplierId, Long trainermanId, Integer isDone, Date doneTime, Date doneFinishTime, Long financeId) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.payment = payment;
		this.paymentType = paymentType;
		this.isPay = isPay;
		this.created = created;
		this.updated = updated;
		this.payTime = payTime;
		this.userId = userId;
		this.buyerMessage = buyerMessage;
		this.buyerNick = buyerNick;
		this.buyerRate = buyerRate;
		this.driverRate = driverRate;
		this.centerRate = centerRate;
		this.centerMessage = centerMessage;
		this.driverMessage = driverMessage;
		this.centerId = centerId;
		this.salemanId = salemanId;
		this.drivermanId = drivermanId;
		this.isDistribute = isDistribute;
		this.isSort = isSort;
		this.isStore = isStore;
		this.isShip = isShip;
		this.distributeTime = distributeTime;
		this.sortTime = sortTime;
		this.storeTime = storeTime;
		this.shipTime = shipTime;
		this.commentTime = commentTime;
		this.outstoreTime = outstoreTime;
		this.supplierId = supplierId;
		this.trainermanId = trainermanId;
		this.isDone = isDone;
		this.doneTime = doneTime;
		this.doneFinishTime = doneFinishTime;
		this.financeId = financeId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public Integer getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}

	public Integer getDriverRate() {
		return driverRate;
	}

	public void setDriverRate(Integer driverRate) {
		this.driverRate = driverRate;
	}

	public Integer getCenterRate() {
		return centerRate;
	}

	public void setCenterRate(Integer centerRate) {
		this.centerRate = centerRate;
	}

	public String getCenterMessage() {
		return centerMessage;
	}

	public void setCenterMessage(String centerMessage) {
		this.centerMessage = centerMessage;
	}

	public String getDriverMessage() {
		return driverMessage;
	}

	public void setDriverMessage(String driverMessage) {
		this.driverMessage = driverMessage;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public Long getSalemanId() {
		return salemanId;
	}

	public void setSalemanId(Long salemanId) {
		this.salemanId = salemanId;
	}

	public Long getDrivermanId() {
		return drivermanId;
	}

	public void setDrivermanId(Long drivermanId) {
		this.drivermanId = drivermanId;
	}

	public Integer getIsDistribute() {
		return isDistribute;
	}

	public void setIsDistribute(Integer isDistribute) {
		this.isDistribute = isDistribute;
	}

	public Integer getIsSort() {
		return isSort;
	}

	public void setIsSort(Integer isSort) {
		this.isSort = isSort;
	}

	public Integer getIsStore() {
		return isStore;
	}

	public void setIsStore(Integer isStore) {
		this.isStore = isStore;
	}

	public Integer getIsShip() {
		return isShip;
	}

	public void setIsShip(Integer isShip) {
		this.isShip = isShip;
	}

	public Date getDistributeTime() {
		return distributeTime;
	}

	public void setDistributeTime(Date distributeTime) {
		this.distributeTime = distributeTime;
	}

	public Date getSortTime() {
		return sortTime;
	}

	public void setSortTime(Date sortTime) {
		this.sortTime = sortTime;
	}

	public Date getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Date getOutstoreTime() {
		return outstoreTime;
	}

	public void setOutstoreTime(Date outstoreTime) {
		this.outstoreTime = outstoreTime;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getTrainermanId() {
		return trainermanId;
	}

	public void setTrainermanId(Long trainermanId) {
		this.trainermanId = trainermanId;
	}

	public Integer getIsDone() {
		return isDone;
	}

	public void setIsDone(Integer isDone) {
		this.isDone = isDone;
	}

	public Date getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public Date getDoneFinishTime() {
		return doneFinishTime;
	}

	public void setDoneFinishTime(Date doneFinishTime) {
		this.doneFinishTime = doneFinishTime;
	}

	public Long getFinanceId() {
		return financeId;
	}

	public void setFinanceId(Long financeId) {
		this.financeId = financeId;
	}

}