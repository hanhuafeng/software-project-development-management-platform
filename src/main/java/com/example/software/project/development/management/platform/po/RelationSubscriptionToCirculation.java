package com.example.software.project.development.management.platform.po;

public class RelationSubscriptionToCirculation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relation_subscription_to_circulation.id
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relation_subscription_to_circulation.subscription_id
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    private Integer subscriptionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column relation_subscription_to_circulation.circulation
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    private Integer circulation;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relation_subscription_to_circulation.id
     *
     * @return the value of relation_subscription_to_circulation.id
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relation_subscription_to_circulation.id
     *
     * @param id the value for relation_subscription_to_circulation.id
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relation_subscription_to_circulation.subscription_id
     *
     * @return the value of relation_subscription_to_circulation.subscription_id
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relation_subscription_to_circulation.subscription_id
     *
     * @param subscriptionId the value for relation_subscription_to_circulation.subscription_id
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column relation_subscription_to_circulation.circulation
     *
     * @return the value of relation_subscription_to_circulation.circulation
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    public Integer getCirculation() {
        return circulation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column relation_subscription_to_circulation.circulation
     *
     * @param circulation the value for relation_subscription_to_circulation.circulation
     *
     * @mbggenerated Mon Jan 25 20:14:02 CST 2021
     */
    public void setCirculation(Integer circulation) {
        this.circulation = circulation;
    }
}