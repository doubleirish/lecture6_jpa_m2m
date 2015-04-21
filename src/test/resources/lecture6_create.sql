
/* embeddable example */
CREATE TABLE USERS (
  ID            INT         NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY,
  USERNAME      VARCHAR(15) NOT NULL CONSTRAINT users_username_UC UNIQUE,
  FIRSTNAME     VARCHAR(50) NOT NULL,
  LASTNAME      VARCHAR(50) NOT NULL,

  BILLING_ADDR_STREET  VARCHAR(255) NOT NULL,
  BILLING_ADDR_CITY    VARCHAR(255) NOT NULL,
  BILLING_ADDR_STATE   VARCHAR(2)   NOT NULL,
  BILLING_ADDR_ZIP     VARCHAR(10)  NOT NULL,
  
  SHIPPING_ADDR_STREET  VARCHAR(255) NOT NULL,
  SHIPPING_ADDR_CITY    VARCHAR(255) NOT NULL,
  SHIPPING_ADDR_STATE   VARCHAR(2)   NOT NULL,
  SHIPPING_ADDR_ZIP     VARCHAR(10)  NOT NULL,

  CREDIT_CARD_NAME            VARCHAR(255) NOT NULL,
  CREDIT_CARD_NUMBER          VARCHAR(16)  NOT NULL,
  CREDIT_CARD_EXPIRATION_DATE DATE         NOT NULL
);

/* Repository Example */

CREATE TABLE GENRE (
  ID      INT          NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY,
  NAME  VARCHAR(255)   NOT NULL
);


/* many to many uni directional (no extra cols)*/
CREATE TABLE COURSE (
  ID             INT           NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
  ,CODE           VARCHAR(10)   NOT NULL  CONSTRAINT crs_code_UC UNIQUE
  ,TITLE          VARCHAR(255)  NOT NULL
  ,DESCRIPTION    VARCHAR(4000) NOT NULL

);


CREATE TABLE STUDENT (
  ID              INT         NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
  ,USERNAME        VARCHAR(15) NOT NULL CONSTRAINT stud_username_UC UNIQUE
  ,FIRSTNAME       VARCHAR(50) NOT NULL
  ,LASTNAME        VARCHAR(50) NOT NULL
  ,DOB   DATE
);


CREATE TABLE STUDENT_COURSE (
  STUDENT_ID         INT          NOT NULL CONSTRAINT enroll_stud_FK REFERENCES STUDENT
  ,COURSE_ID         INT          NOT NULL CONSTRAINT enroll_crs_FK REFERENCES COURSE
  , PRIMARY KEY (STUDENT_ID,COURSE_ID)
);


/* many to many uni directional (no extra cols)*/

CREATE TABLE DIRECTOR (
  ID               INT         NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
  ,FIRSTNAME       VARCHAR(50) NOT NULL
  ,LASTNAME        VARCHAR(50) NOT NULL
  ,DOB             DATE

);


CREATE TABLE BOARD (
  ID                   INT         NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
  ,COMPANY_NAME        VARCHAR(50) NOT NULL CONSTRAINT board_comp_name_UC UNIQUE
  ,SYMBOL              VARCHAR(5)  NOT NULL CONSTRAINT board_sym_UC UNIQUE

);


CREATE TABLE DIRECTOR_BOARD (
  DIRECTOR_ID         INT          NOT NULL CONSTRAINT db_dir_FK REFERENCES DIRECTOR
  ,BOARD_ID           INT          NOT NULL CONSTRAINT db_brd_FK REFERENCES BOARD
 -- , SALARY            INT          NOT NULL
  , PRIMARY KEY (DIRECTOR_ID,BOARD_ID)
);



/* many to many  bi directional with extra columns*/
   CREATE TABLE EMPLOYEE (
        ID              INT          NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
       ,FIRST_NAME       VARCHAR(50) NOT NULL
       ,LAST_NAME        VARCHAR(50) NOT NULL
    );

      CREATE TABLE PROJECT (
        ID                   INT         NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
       ,PROJECT_NAME         VARCHAR(50) NOT NULL CONSTRAINT proj_comp_name_UC UNIQUE

    );

    CREATE TABLE EMPLOYEE_PROJECT (
        EMPLOYEE_ID     INT     NOT NULL  CONSTRAINT pe_emp_FK REFERENCES EMPLOYEE
       ,PROJECT_ID      INT     NOT NULL  CONSTRAINT pe_prj_FK REFERENCES PROJECT
       ,IS_PROJECT_LEAD BOOLEAN DEFAULT false
       ,PRIMARY KEY (EMPLOYEE_ID, PROJECT_ID)
    );


               
       