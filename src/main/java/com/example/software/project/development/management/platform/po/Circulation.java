package com.example.software.project.development.management.platform.po;

public class Circulation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column circulation.id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column circulation.reason
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    private String reason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column circulation.to_person_id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    private Integer toPersonId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column circulation.create_person_id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    private Integer createPersonId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column circulation.id
     *
     * @return the value of circulation.id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column circulation.id
     *
     * @param id the value for circulation.id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column circulation.reason
     *
     * @return the value of circulation.reason
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column circulation.reason
     *
     * @param reason the value for circulation.reason
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column circulation.to_person_id
     *
     * @return the value of circulation.to_person_id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public Integer getToPersonId() {
        return toPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column circulation.to_person_id
     *
     * @param toPersonId the value for circulation.to_person_id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public void setToPersonId(Integer toPersonId) {
        this.toPersonId = toPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column circulation.create_person_id
     *
     * @return the value of circulation.create_person_id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public Integer getCreatePersonId() {
        return createPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column circulation.create_person_id
     *
     * @param createPersonId the value for circulation.create_person_id
     *
     * @mbggenerated Mon Jan 25 21:33:59 CST 2021
     */
    public void setCreatePersonId(Integer createPersonId) {
        this.createPersonId = createPersonId;
    }
}