package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 驾驶员信息
 * A data object class directly models database table <tt>tbl_driver_base_info</tt>.
 */
@Data
public class DriverBaseInfo {
    /**
     * This field corresponds to the database column tbl_driver_base_info.id
     */
    private Integer id;



    /**
     * This field corresponds to the database column tbl_driver_base_info.mobile_operator
     */
    private String mobileOperator;


    /**
     * This field corresponds to the database column tbl_driver_base_info.company_logo
     */
    private String companyLogo;

    /**
     * This field corresponds to the database column tbl_driver_base_info.administrative_code
     */
    private String administrativeCode;

    /**
     * This field corresponds to the database column tbl_driver_base_info.birthday
     */
    private Date birthday;

    /**
     * This field corresponds to the database column tbl_driver_base_info.country
     */
    private String country;

    /**
     * This field corresponds to the database column tbl_driver_base_info.national
     */
    private String national;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driving_licence_number
     */
    private String drivingLicenceNumber;

    /**
     * This field corresponds to the database column tbl_driver_base_info.marital_status
     */
    private String maritalStatus;

    /**
     * This field corresponds to the database column tbl_driver_base_info.foreign_language_ability
     */
    private String foreignLanguageAbility;

    /**
     * This field corresponds to the database column tbl_driver_base_info.app_version
     */
    private String appVersion;

    /**
     * This field corresponds to the database column tbl_driver_base_info.map_type
     */
    private String mapType;

    /**
     * This field corresponds to the database column tbl_driver_base_info.education_background
     */
    private String educationBackground;

    /**
     * This field corresponds to the database column tbl_driver_base_info.household_registration_authority
     */
    private String householdRegistrationAuthority;

    /**
     * This field corresponds to the database column tbl_driver_base_info.registered_permanent_residence_address
     */
    private String registeredPermanentResidenceAddress;

    /**
     * This field corresponds to the database column tbl_driver_base_info.address
     */
    private String address;

    /**
     * This field corresponds to the database column tbl_driver_base_info.address_longitude
     */
    private String addressLongitude;

    /**
     * This field corresponds to the database column tbl_driver_base_info.address_latitude
     */
    private String addressLatitude;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driver_img_file_number
     */
    private String driverImgFileNumber;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driver_license
     */
    private String driverLicense;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driver_license_scan_copy_number
     */
    private String driverLicenseScanCopyNumber;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driving_type
     */
    private String drivingType;

    /**
     * This field corresponds to the database column tbl_driver_base_info.first_get_driver_license_date
     */
    private Date firstGetDriverLicenseDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driver_license_validity_start
     */
    private Date driverLicenseValidityStart;

    /**
     * This field corresponds to the database column tbl_driver_base_info.driver_license_validity_end
     */
    private Date driverLicenseValidityEnd;

    /**
     * This field corresponds to the database column tbl_driver_base_info.is_taxi_driver
     */
    private Integer isTaxiDriver;

    /**
     * This field corresponds to the database column tbl_driver_base_info.network_reservation_taxi_driver_license_number
     */
    private String networkReservationTaxiDriverLicenseNumber;

    /**
     * This field corresponds to the database column tbl_driver_base_info.network_reservation_taxi_driver_license_issuing_agencies
     */
    private String networkReservationTaxiDriverLicenseIssuingAgencies;

    /**
     * This field corresponds to the database column tbl_driver_base_info.certificate_issuing_date
     */
    private Date certificateIssuingDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.first_qualification_date
     */
    private Date firstQualificationDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.qualification_certificate_validity_start
     */
    private Date qualificationCertificateValidityStart;

    /**
     * This field corresponds to the database column tbl_driver_base_info.qualification_certificate_validity_end
     */
    private Date qualificationCertificateValidityEnd;

    /**
     * This field corresponds to the database column tbl_driver_base_info.reported_date
     */
    private Date reportedDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.is_full_time_driver
     */
    private Integer isFullTimeDriver;

    /**
     * This field corresponds to the database column tbl_driver_base_info.is_in_driver_blacklist
     */
    private Integer isInDriverBlacklist;

    /**
     * This field corresponds to the database column tbl_driver_base_info.service_type
     */
    private Integer serviceType;

    /**
     * This field corresponds to the database column tbl_driver_base_info.company
     */
    private String company;

    /**
     * This field corresponds to the database column tbl_driver_base_info.contract_start_date
     */
    private Date contractStartDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.contract_end_date
     */
    private Date contractEndDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.emergency_contact
     */
    private String emergencyContact;

    /**
     * This field corresponds to the database column tbl_driver_base_info.emergency_contact_phone_number
     */
    private String emergencyContactPhoneNumber;

    /**
     * This field corresponds to the database column tbl_driver_base_info.emergency_contact_address
     */
    private String emergencyContactAddress;

    /**
     * This field corresponds to the database column tbl_driver_base_info.training_courses
     */
    private String trainingCourses;

    /**
     * This field corresponds to the database column tbl_driver_base_info.training_courses_date
     */
    private Date trainingCoursesDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.training_courses_start_date
     */
    private Date trainingCoursesStartDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.training_courses_end_date
     */
    private Date trainingCoursesEndDate;

    /**
     * This field corresponds to the database column tbl_driver_base_info.training_courses_time
     */
    private Integer trainingCoursesTime;

    /**
     * This field corresponds to the database column tbl_driver_base_info.bank_name
     */
    private String bankName;

    /**
     * This field corresponds to the database column tbl_driver_base_info.bank_card_number
     */
    private String bankCardNumber;

    /**
     * This field corresponds to the database column tbl_driver_base_info.note
     */
    private String note;

    /**
     * This field corresponds to the database column tbl_driver_base_info.qualification_certificate_img
     */
    private String qualificationCertificateImg;

    /**
     * This field corresponds to the database column tbl_driver_base_info.other_img1
     */
    private String otherImg1;

    /**
     * This field corresponds to the database column tbl_driver_base_info.other_img2
     */
    private String otherImg2;

    /**
     * This field corresponds to the database column tbl_driver_base_info.create_time
     */
    private Date createTime;

    /**
     * This field corresponds to the database column tbl_driver_base_info.update_time
     */
    private Date updateTime;

}