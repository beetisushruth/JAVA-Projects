import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 
 * @author sushruth sb3112
 * @description helper class used to load data into tables for CSCI620 assignment
 * using IMDB Dataset
 *
 */
public class ImdbDataLoadHelper {

	private static final String URL = "jdbc:postgresql://localhost/CSCI620";
	private static final String USER = "sushruth";
	private static final String PASSWORD = "1234";
	private static final String TITLE_SQL = "INSERT INTO sample.title(titleID, titleType, "
			+ "primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes, genres) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String PERSON_SQL = "INSERT INTO person(personID, name, "
			+ "birthYear, deathYear) VALUES(?,?,?,?)";
	private static final String TITLE_PERSON_SQL = "INSERT INTO person_role_in_title("
			+ "personID, titleID, role) VALUES(?, ?, ?) ON CONFLICT DO NOTHING";
	private static final String EPISODE_SQL = "INSERT INTO episode(episodeID, "
			+ "titleID, seasonNumber, episodeNumber) VALUES(?, ?, ?, ?)";
	private static final String TITLE_RATING_SQL = "INSERT INTO title_rating("
			+ "titleID, averageRating, numVotes) VALUES(?, ?, ?)";

	/**
	 * Get databse connection
	 * @param url
	 * @param user
	 * @param pass
	 * @return
	 */
	public static Connection getDatabaseConnection(String url, String user, String pass) {
		Connection connection = null;
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", pass);
		try {
			connection = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Insert episodes to episode table
	 * @param filePath
	 * @param sql
	 * @param batchSize
	 * @param removeHeader
	 */
	public static void insertEpisodes(String filePath, String sql, int batchSize, boolean removeHeader) {
		List<List<String>> data = null;
		boolean shouldRun = true;
		int startRow = 1;
		try (Connection connection = getDatabaseConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			while (shouldRun) {
				data = loadDataFromFile(filePath, startRow, batchSize, removeHeader);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < data.size(); i++) {
						List<String> row = data.get(i);
						Integer episodeID = Integer.parseInt(row.get(0).substring(2));
						Integer titleID = Integer.parseInt(row.get(1).substring(2));
						Integer seasonNumber = row.get(2).equals("\\N") ? null : Integer.parseInt(row.get(2));
						Integer episodeNumber = row.get(3).equals("\\N") ? null : Integer.parseInt(row.get(3));
						statement.setInt(1, episodeID);
						statement.setInt(2, titleID);
						if (seasonNumber != null) {
							statement.setInt(3, seasonNumber);
						} else {
							statement.setNull(3, java.sql.Types.NULL);
						}
						if (seasonNumber != null) {
							statement.setInt(4, episodeNumber);
						} else {
							statement.setNull(4, java.sql.Types.NULL);
						}
						statement.addBatch();
					}
					statement.executeBatch();
					startRow += batchSize;
				}
				shouldRun = data.size() > 0;
				System.out.println("batch done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load data from file
	 * @param filePath
	 * @param startRow
	 * @param numOfRows
	 * @param removeHeader
	 * @return
	 */
	public static List<List<String>> loadDataFromFile(String filePath, int startRow, int numOfRows,
			boolean removeHeader) {
		List<List<String>> data = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			data = br.lines().skip(startRow + (removeHeader ? 1 : 0) - 1).limit(numOfRows).map(line -> {
				return Arrays.asList(line.split("\t"));
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Insert title in title table
	 * @param filePath
	 * @param sql
	 * @param batchSize
	 * @param removeHeader
	 */
	public static void insertTitles(String filePath, String sql, int batchSize, boolean removeHeader) {
		List<List<String>> data = null;
		boolean shouldRun = true;
		int startRow = 1;
		try (Connection connection = getDatabaseConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			while (shouldRun) {
				data = loadDataFromFile(filePath, startRow, batchSize, removeHeader);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < data.size(); i++) {
						List<String> row = data.get(i);
						Integer titleId = Integer.parseInt(row.get(0).substring(2));
						String titleType = row.get(1);
						String primaryTitle = row.get(2);
						String originalTitle = row.get(3);
						Boolean isAdult = row.get(4).equals("0") ? false : true;
						Integer startYear = row.get(5).equals("\\N") ? null : Integer.parseInt(row.get(5));
						Integer endYear = row.get(6).equals("\\N") ? null : Integer.parseInt(row.get(6));
						Integer runtimeMinutes = row.get(7).equals("\\N") ? null : Integer.parseInt(row.get(7));
						String genres = row.get(8);
						statement.setInt(1, titleId);
						statement.setString(2, titleType);
						statement.setString(3, primaryTitle);
						statement.setString(4, originalTitle);
						statement.setBoolean(5, isAdult);
						if (startYear != null) {
							statement.setInt(6, startYear);
						} else {
							statement.setNull(6, java.sql.Types.NULL);
						}
						if (endYear != null) {
							statement.setInt(7, endYear);
						} else {
							statement.setNull(7, java.sql.Types.NULL);
						}
						if (runtimeMinutes != null) {
							statement.setInt(8, runtimeMinutes);
						} else {
							statement.setNull(8, java.sql.Types.NULL);
						}
						statement.setString(9, genres);
						statement.addBatch();
					}
					statement.executeBatch();
					startRow += batchSize;
				}
				shouldRun = data.size() > 0;
				System.out.println("batch done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert persons in person table
	 * @param filePath
	 * @param sql
	 * @param batchSize
	 * @param removeHeader
	 */
	public static void insertPersons(String filePath, String sql, int batchSize, boolean removeHeader) {
		List<List<String>> data = null;
		boolean shouldRun = true;
		int startRow = 1;
		try (Connection connection = getDatabaseConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			while (shouldRun) {
				data = loadDataFromFile(filePath, startRow, batchSize, removeHeader);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < data.size(); i++) {
						List<String> row = data.get(i);
						Integer personID = Integer.parseInt(row.get(0).substring(2));
						String name = row.get(1);
						Integer birthYear = row.get(2).equals("\\N") ? null : Integer.parseInt(row.get(2));
						Integer deathYear = row.get(3).equals("\\N") ? null : Integer.parseInt(row.get(3));
						// this statement was written because data was faulty
						if (birthYear != null && deathYear != null) {
							int temp = Math.min(birthYear, deathYear);
							deathYear = Math.max(birthYear, deathYear);
							birthYear = temp;
						}
						statement.setInt(1, personID);
						statement.setString(2, name);
						if (birthYear != null) {
							statement.setInt(3, birthYear);
						} else {
							statement.setNull(3, java.sql.Types.NULL);
						}
						if (deathYear != null) {
							statement.setInt(4, deathYear);
						} else {
							statement.setNull(4, java.sql.Types.NULL);
						}
						statement.addBatch();
					}
					statement.executeBatch();
					startRow += batchSize;
				}
				shouldRun = data.size() > 0;
				System.out.println("batch done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert persons in title and roles table
	 * @param filePath
	 * @param sql
	 * @param batchSize
	 * @param removeHeader
	 */
	public static void insertPersonsInTitle(String filePath, String sql, int batchSize, boolean removeHeader) {
		List<List<String>> data = null;
		boolean shouldRun = true;
		int startRow = 1;
		try (Connection connection = getDatabaseConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			while (shouldRun) {
				data = loadDataFromFile(filePath, startRow, batchSize, removeHeader);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < data.size(); i++) {
						List<String> row = data.get(i);
						Integer personID = Integer.parseInt(row.get(0).substring(2));
						Integer titleID = Integer.parseInt(row.get(2).substring(2));
						String role = row.get(3);
						statement.setInt(1, personID);
						statement.setInt(2, titleID);
						statement.setString(3, role);
						statement.addBatch();
					}
					statement.executeBatch();
					startRow += batchSize;
				}
				shouldRun = data.size() > 0;
				System.out.println("batch done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert title and ratings in the table
	 * @param filePath
	 * @param sql
	 * @param batchSize
	 * @param removeHeader
	 */
	public static void insertTitleRatings(String filePath, String sql, int batchSize, boolean removeHeader) {
		List<List<String>> data = null;
		boolean shouldRun = true;
		int startRow = 1;
		try (Connection connection = getDatabaseConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			while (shouldRun) {
				data = loadDataFromFile(filePath, startRow, batchSize, removeHeader);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < data.size(); i++) {
						List<String> row = data.get(i);
						Integer titleID = Integer.parseInt(row.get(0).substring(2));
						Double averageRating = Double.parseDouble(row.get(1));
						Integer numVotes = Integer.parseInt(row.get(2));
						statement.setInt(1, titleID);
						statement.setDouble(2, averageRating);
						statement.setInt(3, numVotes);
						statement.addBatch();
					}
					statement.executeBatch();
					startRow += batchSize;
				}
				shouldRun = data.size() > 0;
				System.out.println("batch done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Transaction example for assignment 1 CSCI620
	 * @param sqlQuery1
	 * @param sqlQuery2
	 */
	public static void transact(String sqlQuery1, String sqlQuery2) {
		Connection connection = null;
		try {
			connection = getDatabaseConnection(URL, USER, PASSWORD);

			PreparedStatement insertStatement1 = connection.prepareStatement(sqlQuery1);
			PreparedStatement insertStatement2 = connection.prepareStatement(sqlQuery2);

			// Disable auto commit
			connection.setAutoCommit(false);

			// Create insert statement
			insertStatement1.setInt(1, 987232);
			insertStatement1.setString(2, "James");
			insertStatement1.setInt(3, 1998);
			insertStatement1.setInt(4, 2050);
			insertStatement1.executeUpdate();

			// Create update statement
			insertStatement2.setInt(1, 987233);
			insertStatement2.setString(2, "Ram");
			insertStatement2.setInt(3, 2050);
			insertStatement2.setInt(4, 1998);
			insertStatement2.executeUpdate();

			// Commit inserts
			connection.commit();
			System.out.println("Successfully inserted both the records");
		} catch (SQLException e) {
			if (connection != null) {
				try {
					// Rollback transaction
					connection.rollback();
					e.printStackTrace();
					System.out.println("Transaction is rolled back.");
				} catch (Exception exception) {
					System.out
							.println("Exception occurred while rolling back the transaction " + exception.getMessage());
				}
			}
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Exception occurred while closing the connection");
				}
		}
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		// change file path accordingly
		String filePath = "/Users/sushruth/Documents/MS/CSCI620/title.basics.tsv";
		insertTitles(filePath, TITLE_SQL, 1000000, true);	// time taken 5.30 minutes
		insertPersons(filePath, PERSON_SQL, 1000000, true);     // time taken 5.45 mintues
		insertPersonsInTitle(filePath, TITLE_PERSON_SQL, 1000000, true); // time taken 54.30 minutes
		insertEpisodes(filePath, EPISODE_SQL, 100000, true);		 // time taken ~ 3 minutes 
		insertTitleRatings(filePath, TITLE_RATING_SQL, 100000, true);    // time taken ~ 5 minutes
		transact(PERSON_SQL, PERSON_SQL);

	}
}
