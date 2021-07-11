package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListMatchModelResponse {
    private String page;
    private String pageSize;
    private String page_count;
    private String total_count;
    private List<MatchModelResponse> matchModelResponses;
}
