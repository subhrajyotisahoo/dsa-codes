class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        int full = (1 << n) - 1; // bitmask with n bits set (for all columns)
        return solve(n, full, 0, 0, 0, 0); // added missing 'row' argument
    }

    // Backtracking using bitmasks
    private int solve(int n, int full, int cols, int diag, int anti, int row) {
        if (row == n) return 1; // all queens placed successfully
        int count = 0;

        // find all available positions (1-bits are free spots)
        int avail = full & ~(cols | diag | anti);

        while (avail != 0) {
            int p = avail & -avail; // extract lowest set bit (a valid position)
            avail -= p;             // remove that bit

            // place queen and recurse for next row
            count += solve(n, full, cols | p, (diag | p) << 1, (anti | p) >>> 1, row + 1);
        }

        return count;
    }
}
