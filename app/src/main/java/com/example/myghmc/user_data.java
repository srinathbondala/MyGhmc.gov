package com.example.myghmc;

public class user_data {
        private Long id; // assuming there's an ID field for primary key
        private String name;
        private Long phoneNumber;
        private Long sfaNumber;
        private String colonyName;
        private String plotNo;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Long getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(Long phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public Long getSfaNumber() {
                return sfaNumber;
        }

        public void setSfaNumber(Long sfaNumber) {
                this.sfaNumber = sfaNumber;
        }

        public String getColonyName() {
                return colonyName;
        }

        public void setColonyName(String colonyName) {
                this.colonyName = colonyName;
        }

        public String getPlotNo() {
                return plotNo;
        }

        public void setPlotNo(String plotNo) {
                this.plotNo = plotNo;
        }

        public Long getRoadNumber() {
                return roadNumber;
        }

        public void setRoadNumber(Long roadNumber) {
                this.roadNumber = roadNumber;
        }

        public Long getTotalUnits() {
                return totalUnits;
        }

        public void setTotalUnits(Long totalUnits) {
                this.totalUnits = totalUnits;
        }

        public Long getCommercialUnits() {
                return commercialUnits;
        }

        public void setCommercialUnits(Long commercialUnits) {
                this.commercialUnits = commercialUnits;
        }

        public Long getResidentialUnits() {
                return residentialUnits;
        }

        public void setResidentialUnits(Long residentialUnits) {
                this.residentialUnits = residentialUnits;
        }

        public String getZone() {
                return zone;
        }

        public void setZone(String zone) {
                this.zone = zone;
        }

        public Long getCircle() {
                return circle;
        }

        public void setCircle(Long circle) {
                this.circle = circle;
        }

        public Long getWard() {
                return ward;
        }

        public void setWard(Long ward) {
                this.ward = ward;
        }

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getLatitudeAndLongitude() {
                return latitudeAndLongitude;
        }

        public void setLatitudeAndLongitude(String latitudeAndLongitude) {
                this.latitudeAndLongitude = latitudeAndLongitude;
        }

        public String getQrCodeData() {
                return qrCodeData;
        }

        public void setQrCodeData(String qrCodeData) {
                this.qrCodeData = qrCodeData;
        }

        public Boolean getWasteCollected() {
                return isWasteCollected;
        }

        public void setWasteCollected(Boolean wasteCollected) {
                isWasteCollected = wasteCollected;
        }

        public String getDriverSat() {
                return driverSat;
        }

        public void setDriverSat(String driverSat) {
                this.driverSat = driverSat;
        }

        public String getWasteCollectedTime() {
                return wasteCollectedTime;
        }

        public void setWasteCollectedTime(String wasteCollectedTime) {
                this.wasteCollectedTime = wasteCollectedTime;
        }

        private Long roadNumber,totalUnits;

        private Long commercialUnits,residentialUnits;
        private String zone;
        private Long circle,ward;
        private String userId,latitudeAndLongitude,qrCodeData;
        private Boolean isWasteCollected;
        private String driverSat,wasteCollectedTime;

        public user_data(Long id, String name, Long phoneNumber, Long sfaNumber, String colonyName, String plotNo, Long roadNumber, Long totalUnits, Long commercialUnits, Long residentialUnits, String zone, Long circle, Long ward, String userId, String latitudeAndLongitude, String qrCodeData, Boolean isWasteCollected, String driverSat, String wasteCollectedTime) {
                this.id = id;
                this.name = name;
                this.phoneNumber = phoneNumber;
                this.sfaNumber = sfaNumber;
                this.colonyName = colonyName;
                this.plotNo = plotNo;
                this.roadNumber = roadNumber;
                this.totalUnits = totalUnits;
                this.commercialUnits = commercialUnits;
                this.residentialUnits = residentialUnits;
                this.zone = zone;
                this.circle = circle;
                this.ward = ward;
                this.userId = userId;
                this.latitudeAndLongitude = latitudeAndLongitude;
                this.qrCodeData = qrCodeData;
                this.isWasteCollected = isWasteCollected;
                this.driverSat = driverSat;
                this.wasteCollectedTime = wasteCollectedTime;
        }
}
