package utn.tacs.dto.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListPlayerModelResponse {
    private String page;
    private String pageSize;
    private String page_count;
    private String total_count;
    private List<User> users;
}
