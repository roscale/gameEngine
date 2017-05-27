package test;

/**
 * Created by roscale on 5/3/17.
 */
public class Grid
{
	public Cell[][] cells;

	public Grid(float x, float y, int w, int h, float cellW, float cellH)
	{
		cells = new Cell[w][h];

		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++)
				cells[i][j] = new Cell(x + i*cellW, y + j*cellH, cellW, cellH);
	}
}
