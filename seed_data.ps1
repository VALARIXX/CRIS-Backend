$names = @("Arjun", "Balaji", "Vishnu", "Praveen", "Kishore", "Sanjay", "Dinesh", "Suriya", "Mohan")
$cities = @("Chennai", "Coimbatore", "Madurai", "Trichy", "Salem", "Tirunelveli", "Erode", "Vellore", "Thoothukudi")

for ($i=0; $i -lt $names.Length; $i++) {
    $name = $names[$i]
    $city = $cities[$i]
    $aadhar = "80008000700$i"
    $body = @{
        fullName = $name
        aadharNumber = $aadhar
        dateOfBirth = "1995-05-15"
        gender = "Male"
        address = "123 Main St, $city, Tamil Nadu - 60000$i"
        mobile = "987654321$i"
        residencyStatus = "Active"
    }
    
    $json = $body | ConvertTo-Json
    try {
        $response = Invoke-RestMethod -Uri "http://localhost:8082/api/citizens/enroll" -Method Post -Body $json -ContentType "application/json"
        Write-Host "Created citizen: $($response.fullName) with ID: $($response.citizenId)"

        $householdBody = @{
            headCitizenId = $response.citizenId
            relations = @{}
        }
        $hJson = $householdBody | ConvertTo-Json
        $hResponse = Invoke-RestMethod -Uri "http://localhost:8082/api/citizens/household" -Method Post -Body $hJson -ContentType "application/json"
        Write-Host "Created household: $($hResponse.id)"
    } catch {
        Write-Error "Error inserting $name : $_"
    }
}
