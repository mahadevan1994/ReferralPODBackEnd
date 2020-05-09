    var rf = document.forms['userReferralForm']['referralFrequency'];
    var refdate = document.forms['userReferralForm']['referralEnablementDate'];
    var rlx =document.forms['userReferralForm']['referralinkExpiry'];
    var rbx = document.forms['userReferralForm']['referralBenifitExpiry'];    
    var errorFreq = document.getElementById("refFreq");
    var errorDate = document.getElementById("refdate");
    var errorLinkExp = document.getElementById("reflinkexp");
    var errorBnftExp = document.getElementById("refBnftexp");
         
    rf.addEventListener('blur', freqVerify, true);
    refdate.addEventListener('blur', dateVerify, true);
    rlx.addEventListener('blur',linkVerify,true);
    rbx.addEventListener('blur',benefitVerify,true);
    
    
    //Frequency
    function validate(){
     if (rf.value == null || rf.value == "") {
         rf.style.border = "1px solid red";
         errorFreq.textContent = "Frequency is required";
         rf.focus();
            return false;
     }
     if(isNaN(rf.value)){
         rf.style.border = "1px solid red";
         errorFreq.textContent = "Enter Frequency in digits" ;
         rf.focus();
         return false;
     }
     
     //Date
     if (refdate.value == null || refdate.value == "") {
         refdate.style.border = "1px solid red";
         errorDate.textContent = "Date is required";
         refdate.focus();
            return false;
     } 
     
     //Link Expiry
     if (rlx.value == null || rlx.value == "") {
         rlx.style.border = "1px solid red";
         errorLinkExp.textContent = "Linkexpiry is required";
         rlx.focus();
            return false;
     } 
     if(isNaN(rlx.value)){
         rlx.style.border = "1px solid red";
         errorLinkExp.textContent = "Enter Link expiry in digits" ;
         rlx.focus();
         return false;
     }
     //Benefit Expiry
     if (rbx.value ==null ||rbx.value == "") {
         rbx.style.border = "1px solid red";
         errorBnftExp.textContent = "BenefitExpiry is required";
         rbx.focus();
            return false;
     } 
     if(isNaN(rbx.value)){
         rbx.style.border = "1px solid red";
         errorBnftExp.textContent = "Enter Benefit expiry in digits" ;
         rbx.focus();
         return false;
     }         
}    
     function freqVerify() {
          if (rf.value != "") {
           rf.style.border = "1px solid #5e6e66";
           errorFreq.innerHTML = "";
           return true;
          }
        }
     
     function dateVerify() {
          if (refdate.value != "") {
              refdate.style.border = "1px solid #5e6e66";
              errorDate.innerHTML = "";
           return true;
          }
        }
     function linkVerify() {
          if (rlx.value != "") {
              rlx.style.border = "1px solid #5e6e66";
              errorLinkExp.innerHTML = "";
           return true;
          }
        }
     function benefitVerify() {
          if (rbx.value != "") {
              rbx.style.border = "1px solid #5e6e66";
              errorBnftExp.innerHTML = "";
           return true;
          }


     }
    
