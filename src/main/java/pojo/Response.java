package pojo;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response{
    public String first_name;
    public int id;
    public String last_name;
    public String home_town;
    public String status;
    public String bdate;
    public int bdate_visibility;
    public City city;
    public Country country;
    public String phone;
    public int relation;
    public RelationPartner relation_partner;
    public List<RelationRequest> relation_requests;
    public int sex;
}
