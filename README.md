# Genetic-N-Queens

This is a genetic algorithm solution to the N-Queens problem. 

### Chromosome

For the board, we know that each row and column must only contain 1 queen. Taking this into account, we represent the board with a 1D int array of size N, with each index containing a unique value ranging from 0 to N-1. 

To generate a random chromosome, we initialize the array with each index, i, countaining the value i (array[i] = i). Then, we randomize the arrays sequence. 

### Reproduction

For the selection of parents to reproduce, we use a combination of elitist selection and tournament selection: we copy over the elites into the new population and fill the remaining spots in the new population using tournament selection.

When we reproduce, we take parent1 and copy over indexes 0 to index i. Then we iterate through parent2 looking for values we haven't seen in the child yet. 

For mutation, we swap 2 values in the array.

### Results

This algorithm was tested successfully for up to N = 300 using the values:

```java
public static final int N = 300; 
public static final double MUTATION_RATE = 0.004;
public static final int POPULATION = 150; 
public static final int GENERATIONS = 3000;
public static final int TOURNAMENT_SIZE = 20;
public static final int ELITE_SIZE = 70; 
```

This solution was found in 1703 generations:

```java
{98, 153, 133, 128, 263, 162, 81, 184, 277, 132, 158, 181, 136, 130, 215, 220, 241, 89, 281, 13, 97, 115, 149, 35, 250, 23, 69, 11, 127, 39, 268, 245, 224, 233, 298, 216, 209, 148, 140, 195, 71, 55, 293, 117, 179, 10, 143, 20, 189, 112, 146, 166, 113, 218, 96, 82, 62, 76, 196, 258, 297, 125, 169, 295, 172, 65, 63, 177, 223, 199, 248, 138, 9, 114, 285, 108, 2, 99, 64, 74, 93, 118, 84, 269, 54, 6, 200, 45, 175, 273, 14, 212, 28, 119, 183, 52, 27, 73, 226, 102, 201, 139, 57, 279, 291, 144, 186, 253, 90, 18, 15, 176, 251, 287, 70, 111, 8, 188, 154, 21, 270, 247, 83, 141, 173, 278, 51, 17, 47, 292, 203, 41, 252, 161, 194, 205, 16, 242, 104, 257, 284, 142, 165, 78, 67, 240, 193, 296, 229, 202, 206, 95, 30, 272, 260, 221, 289, 53, 294, 227, 151, 129, 150, 225, 5, 265, 34, 58, 110, 101, 261, 91, 32, 282, 40, 244, 230, 36, 61, 164, 228, 266, 207, 50, 135, 103, 243, 60, 122, 38, 26, 274, 66, 214, 3, 239, 286, 204, 167, 29, 192, 235, 147, 254, 283, 234, 49, 86, 33, 259, 92, 123, 267, 124, 213, 190, 68, 105, 1, 152, 174, 48, 231, 42, 24, 120, 246, 170, 4, 219, 180, 191, 94, 238, 182, 46, 106, 79, 197, 211, 56, 256, 37, 156, 109, 44, 163, 271, 178, 72, 290, 80, 168, 0, 7, 236, 185, 160, 22, 222, 264, 59, 116, 75, 280, 126, 121, 299, 155, 100, 237, 249, 43, 107, 159, 25, 19, 217, 134, 85, 131, 208, 210, 232, 255, 157, 87, 145, 262, 276, 187, 137, 275, 77, 31, 88, 171, 12, 198, 288};
```
