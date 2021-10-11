package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelationPartner {
    @SerializedName("first_name")
    public String firstName;
    public int id;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("can_access_closed")
    public boolean canAccessClosed;
    @SerializedName("is_closed")
    public boolean isClosed;
}
