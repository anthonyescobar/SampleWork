#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

char* madlib_by_numbers(char* template, int word_count, char* words[])
{
    char* numArray[10];
    numArray[0] = "0";
    numArray[1] = "1";
    numArray[2] = "2";
    numArray[3] = "3";
    numArray[4] = "4";
    numArray[5] = "5";
    numArray[6] = "6";
    numArray[7] = "7";
    numArray[8] = "8";
    numArray[9] = "9";

    int size = strlen(template);

    for (int j = 0; j < strlen(template); ++j)
    {
        while (isdigit(template[j])) {
            for (int i = 0; i < 10; ++i)
            {
                if (i >= word_count)
                {
                    ++j;
                    break;
                }
                if (template[j] == *numArray[i])
                {   
                    j++;
                    size = size + strlen(words[i]);
                    --size;
                    break;
                }
            }
        }
    }

    char* libbed = (char*)malloc(size * sizeof(char));

    int counter = 0;

    for (int j = 0; j < strlen(template); ++j)
    {
        while (isdigit(template[j])) {
            for (int i = 0; i < 10; ++i)
            {                
                if (i >= word_count)
                {
                    libbed[counter] = template[j];
                    counter++;
                    ++j;
                    break;
                }
                if (template[j] == *numArray[i])
                {
                    j++;
            
                    int tempSize = strlen(words[i]);

                    for (int k = 0; k < tempSize; ++k)
                    {
                        libbed[counter] = words[i][k];
                        counter++;
                    }
                    break;
                } 
            }
        }
        libbed[counter] = template[j];
        counter++;
    }
    return libbed;
}