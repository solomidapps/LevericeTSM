package models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Folder {
    public String position;
    public String name;
    public String description;
    public String privacy;
    public String sortingSubchannels;
}
