package pojo;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelationRequest {
    public String first_name;
    public int id;
    public String last_name;
    public boolean can_access_closed;
    public boolean is_closed;
}
