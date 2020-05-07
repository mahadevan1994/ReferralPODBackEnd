function validate()
{
    var a = parseInt(document.getElementById("referralFrequency"));
   
    document.write("Hello validations"+a);
   // int fq = Integer.parseInt(freq);
    if(a=="")
    {   
        alert('enter frequency');
        reffreq.innerHTML='Enter Frequency';
        return false;
    }
    else if(isNaN(a)) {
        alert("enter only digits");
        return false;
        }
   
    else { true; }     
}