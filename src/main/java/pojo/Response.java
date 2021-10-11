package pojo;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response{
    @SerializedName("first_name")
    public String firstName;
    public int id;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("home_town")
    public String homeTown;
    public String status;
    @SerializedName("bdate")
    public String bDate;
    @SerializedName("bdate_visibility")
    public int bDateVisibility;
    public City city;
    public Country country;
    public String phone;
    public int relation;
    public RelationPartner relation_partner;
    public List<RelationRequest> relation_requests;
    public int sex;
}
