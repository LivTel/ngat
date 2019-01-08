/**
 *
 *
 *
 *
 */


#ifndef _NGTCS_ASTROMETRY_H
#define _NGTCS_ASTROMETRY_H

#include"astro_structs.h"

void   ast_init_generic_data( struct all_data_struct *all_data );

void   ast_mean_2_apparent( struct all_data_struct *all_data );

void   ast_LST_rotation( struct all_data_struct *all_data );

void   ast_apparent_2_observed( struct all_data_struct *all_data );

void   ast_observed_2_apparent( struct all_data_struct *all_data );

void   ast_LST_derotation( struct all_data_struct *all_data );

void   ast_apparent_2_mean( struct all_data_struct *all_data );

void   ast_parse_timestamp( struct all_data_struct *all_data );

#endif
