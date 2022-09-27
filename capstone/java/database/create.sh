#!/bin/bash
export PGPASSWORD='postgres1'
BASEDIR=$(dirname $0)
DATABASE=final_capstone
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"
psql -U postgres -d $DATABASE -f "$BASEDIR/vehicle_table.sql"
psql -U postgres -d $DATABASE -f "$BASEDIR/service_request.sql"
psql -U postgres -d $DATABASE -f "$BASEDIR/repair_estimate.sql"
psql -U postgres -d $DATABASE -f "$BASEDIR/insertIntoTables.sql"

