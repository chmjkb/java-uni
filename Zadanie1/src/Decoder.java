import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Decoder {
    StringBuilder decodedData = new StringBuilder();
    StringBuilder inputData = new StringBuilder();
    // lists for keeping track of already saved data and repeat sections
    List<String> dataSections = new ArrayList<>();
    List<Integer> repeatSections = new ArrayList<>();
    int dataBeginningIndex = 0;  // points to the index where current data section begins

    public void input(byte value){
        List<Byte> allowedChars = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(Integer::byteValue).toList();
        if (allowedChars.contains(value)) {
            // checks input for data we can't process
            inputData.append(value);
        }
    }

    public String output(){
        for (int i = 0; i < inputData.length(); ++i){
            if ((inputData.charAt(i)) == '0') {  // we will encounter repeat part after '0'
                dataSections.add(inputData.substring(dataBeginningIndex, i));
                repeatSections.add(Integer.parseInt(inputData.substring(i + 1, i + 5)));
                i += 4;  // skipping to the next data section, if it exists
            } else {
                continue;
            }
            dataBeginningIndex = i + 1;  // appending counter so we keep track of data indexes
        }
        for (int i = 0; i < dataSections.toArray().length; i++){
            // merges results from both lists and returns the output
            decodedData.append(dataSections.get(i).repeat(repeatSections.get(i)));
        }
        return decodedData.toString();
    }

    public void reset(){
        inputData.setLength(0);
    }
}
