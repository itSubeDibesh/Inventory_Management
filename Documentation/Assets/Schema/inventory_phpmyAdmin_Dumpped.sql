-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2021 at 06:37 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `access`
--

CREATE TABLE `access`
(
    `AccessId`    bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `Name`        varchar(50) NOT NULL COMMENT 'Unique Access Assigned to Users',
    `Description` text                 DEFAULT NULL COMMENT 'Access Description',
    `CreatedAt`   timestamp   NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`   timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers`
(
    `CustomerId` bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `TPIN`       varchar(25)  NOT NULL COMMENT 'Save Customers Transaction PIN ',
    `Name`       varchar(200) NOT NULL COMMENT 'Save Customers Name',
    `Address`    text         NOT NULL COMMENT 'Save Customers Address ',
    `Contact`    bigint(20) NOT NULL COMMENT ' Save Customers Contact ',
    `CreatedAt`  timestamp    NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`  timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update '
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `damagedandexpiry`
--

CREATE TABLE `damagedandexpiry`
(
    `DEId`      bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `ProductId` bigint(20) NOT NULL COMMENT 'Link Product to WarehouseAndMartProducts',
    `Type`      enum('Damage','Expiry') NOT NULL DEFAULT 'Expiry' COMMENT 'Save Damage or Expiry',
    `Qunatity`  double    NOT NULL COMMENT 'Damaged or Exxpired Qunatity',
    `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `logins`
--

CREATE TABLE `logins`
(
    `LoginId`   bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `GroupId`   int(11) NOT NULL COMMENT 'Link Logins with User Group',
    `Phone`     bigint(20) NOT NULL COMMENT 'Save Users Phone Number',
    `Email`     text        NOT NULL COMMENT 'Save Users Email Address',
    `Image`     text NULL COMMENT 'Save Users Image',
    `UserName`  varchar(20) NOT NULL COMMENT 'Save User Name To Access Login',
    `Password`  text        NOT NULL COMMENT 'Save Password To Access Login',
    `CreatedAt` timestamp   NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product`
(
    `ProductId`      bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `CategoryId`     int(11) NOT NULL COMMENT 'Link Categories to Products',
    `VendorsName`    varchar(200)          DEFAULT NULL COMMENT 'Products Vendors Name',
    `Name`           varchar(200) NOT NULL COMMENT 'Products Name',
    `Invoice Number` varchar(25)  NOT NULL COMMENT 'Products Purchase Invoice',
    `Invoice Date`   date         NOT NULL COMMENT 'Products Purchase Date',
    `CreatedAt`      timestamp    NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`      timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `productcategories`
--

CREATE TABLE `productcategories`
(
    `CategoryId` int(11) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `ParentId`   int(11) DEFAULT NULL COMMENT 'Link Own Self if Sub Categories Exists',
    `Name`       varchar(200) NOT NULL COMMENT 'Categories Name',
    `Remarks`    text                  DEFAULT NULL COMMENT 'Categories Remarks',
    `CreatedAt`  timestamp    NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`  timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roleaccess`
--

CREATE TABLE `roleaccess`
(
    `RoleAccessId` bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `GroupId`      int(11) NOT NULL COMMENT 'Link Logins with User Group',
    `RoleId`       int(11) NOT NULL COMMENT 'Link Role to RoleAccess Table',
    `AccessId`     bigint(20) NOT NULL COMMENT 'Link Access to RoleAccess Table',
    `Status`       enum('Active','Inactive') NOT NULL DEFAULT 'Active' COMMENT 'Save RoleAccess To Active or Inactive',
    `CreatedAt`    timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`    timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles`
(
    `RoleId`      int(11) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `Name`        varchar(50) NOT NULL COMMENT 'Unique Role Assigned to Users',
    `Description` text                 DEFAULT NULL COMMENT 'Role Description',
    `CreatedAt`   timestamp   NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`   timestamp NULL DEFAULT NULL COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales`
(
    `SalesId`        bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `InvoiceNumber`  varchar(25) NOT NULL COMMENT 'Products Sales Invoice',
    `InvoiceDate`    date        NOT NULL COMMENT 'Products Sales Date  ',
    `ProductId`      bigint(20) NOT NULL COMMENT 'Link Sales to Products ',
    `CustomerId`     bigint(20) NOT NULL COMMENT 'Link Sales to Customers ',
    `UserId`         bigint(20) NOT NULL COMMENT 'Link Sales to User',
    `AmountReceived` double      NOT NULL COMMENT 'Save Amounts Received ',
    `AmountReturned` double      NOT NULL COMMENT 'Save Amounts Returned',
    `CreatedAt`      timestamp   NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert ',
    `UpdatedAt`      timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update '
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usergroup`
--

CREATE TABLE `usergroup`
(
    `GroupId`      int(11) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `Group Number` varchar(50) NOT NULL COMMENT 'Group Name '
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users`
(
    `UserId`        bigint(20) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `LoginId`       bigint(20) NOT NULL COMMENT 'Link Login to Users Table',
    `FullName`      varchar(200) NOT NULL COMMENT 'Save User Full Name',
    `ContactNumber` bigint(20) NOT NULL COMMENT 'Save Users Contact Number',
    `Address`       text         NOT NULL COMMENT 'Save Users Address',
    `DOB`           date         NOT NULL COMMENT 'Save Users Date of Birth',
    `Gender`        enum('Male','Female','Others') NOT NULL COMMENT 'Save Users Gender',
    `TPIN`          varchar(25)  NOT NULL COMMENT 'Save Users Transaction PIN',
    `CreatedAt`     timestamp    NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`     timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `warehouseandmart`
--

CREATE TABLE `warehouseandmart`
(
    `WMId`        int(11) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `Name`        varchar(200) NOT NULL COMMENT 'Warehouse or Mart Name',
    `Type`        enum('Warehouse','Mart') NOT NULL DEFAULT 'Warehouse' COMMENT 'Save Warehouse or Mart',
    `Address`     text         NOT NULL COMMENT 'Save Warehouse or Mart Address',
    `Contact`     bigint(20) NOT NULL COMMENT 'Save Warehouse or Mart Contact',
    `Description` text                  DEFAULT NULL COMMENT 'Warehouse or Mart Contact Description',
    `CreatedAt`   timestamp    NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`   timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `warehouseandmartproducts`
--

CREATE TABLE `warehouseandmartproducts`
(
    `WMProductId`    int(11) NOT NULL COMMENT 'Has Primary key and used to maintain identity',
    `WMId`           int(11) NOT NULL COMMENT '	Link WarehouseAndMart to WarehouseAndMartProducts',
    `ProductId`      bigint(20) NOT NULL COMMENT 'Link Product to WarehouseAndMartProducts',
    `AvailableQTY`   double    NOT NULL COMMENT 'Available Qunatity',
    `PurchasedQTY`   double    NOT NULL COMMENT 'Purchased Qunatity',
    `PurchasedPrice` double    NOT NULL COMMENT 'Purchased Price',
    `SellingPrice`   double    NOT NULL COMMENT 'Selling Price',
    `CreatedAt`      timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Save Current Time Stamp on Insert',
    `UpdatedAt`      timestamp NULL DEFAULT NULL ON UPDATE current_timestamp () COMMENT 'Save Current Time Stamp on Update'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access`
--
ALTER TABLE `access`
    ADD PRIMARY KEY (`AccessId`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
    ADD PRIMARY KEY (`CustomerId`),
  ADD UNIQUE KEY `TPIN` (`TPIN`,`Contact`);

--
-- Indexes for table `damagedandexpiry`
--
ALTER TABLE `damagedandexpiry`
    ADD PRIMARY KEY (`DEId`),
  ADD KEY `DamagedAndExpiry_FK_Product_ProductId` (`ProductId`);

--
-- Indexes for table `logins`
--
ALTER TABLE `logins`
    ADD PRIMARY KEY (`LoginId`),
  ADD UNIQUE KEY `Phone` (`Phone`,`Email`,`UserName`) USING HASH,
  ADD KEY `Logins_FK_UserGroup_GroupId` (`GroupId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`ProductId`),
  ADD KEY `Product_Fk_ProductCategories_CategoryId` (`CategoryId`);

--
-- Indexes for table `productcategories`
--
ALTER TABLE `productcategories`
    ADD PRIMARY KEY (`CategoryId`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `ProductCategories_FK_ProductCategories_ParentId` (`ParentId`);

--
-- Indexes for table `roleaccess`
--
ALTER TABLE `roleaccess`
    ADD PRIMARY KEY (`RoleAccessId`),
  ADD KEY `RoleAccess_FK_Roles_RoleId` (`RoleId`),
  ADD KEY `RoleAccess_FK_Access_AccessId` (`AccessId`),
  ADD KEY `RoleAccess_FK_UserGroup_GroupId` (`GroupId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
    ADD PRIMARY KEY (`RoleId`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
    ADD PRIMARY KEY (`SalesId`),
  ADD KEY `Sales_FK_Product_ProductId` (`ProductId`),
  ADD KEY `Sales_FK_Customers_CustomerId` (`CustomerId`),
  ADD KEY `Sales_FK_Users_UserId` (`UserId`);

--
-- Indexes for table `usergroup`
--
ALTER TABLE `usergroup`
    ADD PRIMARY KEY (`GroupId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`UserId`),
  ADD UNIQUE KEY `ContactNumber` (`ContactNumber`,`TPIN`),
  ADD KEY `Users_FK_Logins_LoginId` (`LoginId`);

--
-- Indexes for table `warehouseandmart`
--
ALTER TABLE `warehouseandmart`
    ADD PRIMARY KEY (`WMId`),
  ADD UNIQUE KEY `Contact` (`Contact`);

--
-- Indexes for table `warehouseandmartproducts`
--
ALTER TABLE `warehouseandmartproducts`
    ADD PRIMARY KEY (`WMProductId`),
  ADD KEY `WarehouseAndMartProducts_FK_WarehouseAndMart_WMId` (`WMId`),
  ADD KEY `WarehouseAndMartProducts_FK_Product_ProductId` (`ProductId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access`
--
ALTER TABLE `access`
    MODIFY `AccessId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
    MODIFY `CustomerId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `damagedandexpiry`
--
ALTER TABLE `damagedandexpiry`
    MODIFY `DEId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `logins`
--
ALTER TABLE `logins`
    MODIFY `LoginId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
    MODIFY `ProductId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `productcategories`
--
ALTER TABLE `productcategories`
    MODIFY `CategoryId` int (11) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `roleaccess`
--
ALTER TABLE `roleaccess`
    MODIFY `RoleAccessId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
    MODIFY `RoleId` int (11) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
    MODIFY `SalesId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `usergroup`
--
ALTER TABLE `usergroup`
    MODIFY `GroupId` int (11) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `UserId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `warehouseandmart`
--
ALTER TABLE `warehouseandmart`
    MODIFY `WMId` int (11) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- AUTO_INCREMENT for table `warehouseandmartproducts`
--
ALTER TABLE `warehouseandmartproducts`
    MODIFY `WMProductId` int (11) NOT NULL AUTO_INCREMENT COMMENT 'Has Primary key and used to maintain identity';

--
-- Constraints for dumped tables
--

--
-- Constraints for table `damagedandexpiry`
--
ALTER TABLE `damagedandexpiry`
    ADD CONSTRAINT `DamagedAndExpiry_FK_Product_ProductId` FOREIGN KEY (`ProductId`) REFERENCES `product` (`ProductId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `logins`
--
ALTER TABLE `logins`
    ADD CONSTRAINT `Logins_FK_UserGroup_GroupId` FOREIGN KEY (`GroupId`) REFERENCES `usergroup` (`GroupId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
    ADD CONSTRAINT `Product_Fk_ProductCategories_CategoryId` FOREIGN KEY (`CategoryId`) REFERENCES `productcategories` (`CategoryId`);

--
-- Constraints for table `productcategories`
--
ALTER TABLE `productcategories`
    ADD CONSTRAINT `ProductCategories_FK_ProductCategories_ParentId` FOREIGN KEY (`ParentId`) REFERENCES `productcategories` (`CategoryId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `roleaccess`
--
ALTER TABLE `roleaccess`
    ADD CONSTRAINT `RoleAccess_FK_Access_AccessId` FOREIGN KEY (`AccessId`) REFERENCES `access` (`AccessId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RoleAccess_FK_Roles_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`RoleId`) ON
DELETE
CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RoleAccess_FK_UserGroup_GroupId` FOREIGN KEY (`GroupId`) REFERENCES `usergroup` (`GroupId`) ON DELETE
CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
    ADD CONSTRAINT `Sales_FK_Customers_CustomerId` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`CustomerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Sales_FK_Product_ProductId` FOREIGN KEY (`ProductId`) REFERENCES `product` (`ProductId`) ON
DELETE
CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Sales_FK_Users_UserId` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE
CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
    ADD CONSTRAINT `Users_FK_Logins_LoginId` FOREIGN KEY (`LoginId`) REFERENCES `logins` (`LoginId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `warehouseandmartproducts`
--
ALTER TABLE `warehouseandmartproducts`
    ADD CONSTRAINT `WarehouseAndMartProducts_FK_Product_ProductId` FOREIGN KEY (`ProductId`) REFERENCES `product` (`ProductId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `WarehouseAndMartProducts_FK_WarehouseAndMart_WMId` FOREIGN KEY (`WMId`) REFERENCES `warehouseandmart` (`WMId`) ON
DELETE
CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
